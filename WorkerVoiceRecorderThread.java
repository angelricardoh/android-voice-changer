package com.tvnichos.denuncias.botonpanico.activity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.vinuxproject.sonic.SonicPitchChanger;

import com.mobiik.android.botonpanico.uploader.BotonPanicoService;
import com.mobiik.android.botonpanico.uploader.Event;
import com.mobiik.android.botonpanico.uploader.UploaderCode;
import com.mobiik.android.exception.UnknowException;


/**
 *
 * @author Tim
 * @param <SonicPitchChanger>
 */
public class WorkerVoiceRecorderThread extends Thread{

	public static boolean primerHilo = true;
	Handler statusBackMainThreadHandler = null;
	private static boolean isRecording;
	private String fileLocation;
	static int i;
	public static boolean stopped = false;
	BotonPanicoActivo bpa;
	private int idEvento;
	ExtAudioRecorder extAudioRecorder;
	private static Logger mLog;
	private int runs = 0;
	private WorkerVoiceRecorderThread recordSystem;
	// Define the connection-string with your values


	public WorkerVoiceRecorderThread(BotonPanicoActivo bpa, String fileLocation, int idEvento) {
		mLog = Logger.getLogger("WorkerVoiceRecorderThread");
		this.bpa = bpa;
		this.fileLocation = fileLocation;
		this.idEvento = idEvento;
		//statusBackMainThreadHandler = h; 
	}

	public synchronized int getRuns(){
		return runs;
	}

	public synchronized void stopRecord() {
		isRecording = false;
	}

	public synchronized void stopTask(){
		mLog.info("endingThread");
		
		stopped = true;
		if (recordSystem != null){
			recordSystem.stopRecord();
		}
		
	}

	public synchronized void run() {

		runs++;

		mLog.info("Running thread");
		if (stopped){
			runs--;
			return;
		}

		mLog.info("Starting thread");


		String normalAudioFile = fileLocation+System.currentTimeMillis()+".wav";


		try {

			mLog.info("Voice recorder thread - Inicia configuraci—n");
			extAudioRecorder = ExtAudioRecorder.getInstanse(false); // Uncompressed recording (WAV)
			mLog.info("Creating instance");
			if (extAudioRecorder == null)
			{
				mLog.info("extAudioRecorder is null");
				runs--;
				return;
			}
			else{
				ExtAudioRecorder.State actualState = extAudioRecorder.getState();
				if (actualState == ExtAudioRecorder.State.ERROR);
				{
					mLog.info("ExtAudioRecorder State.ERROR");

				} 
			}

			mLog.info("Preparing audio recorder");
			extAudioRecorder.setOutputFile(normalAudioFile);

			int status = extAudioRecorder.prepare();
			mLog.info("WorkerVoiceRecorder status = " + String.valueOf(status));
			if (status == 1){
				runs--;
				return;
			}

			mLog.info("Voice recorder thread - Termina configuraci—n");

			mLog.info("Voice recorder thread - Inicia timer");
			extAudioRecorder.start();

			isRecording = true;


			try {
				for(int j=0; j<30; j++)
				{
					if (isRecording == false)
						break;

					sleep(500);

					if (stopped)
					{
						isRecording = false;
						break;
					}
				}
			}
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				mLog.error("InterruptedException : " + e.getMessage());
			}

			mLog.info("Voice recorder thread - Detiene timer");
			extAudioRecorder.stop();

			extAudioRecorder.release();


			mLog.info("Voice recorder thread - Starts storing file");
			mLog.info("WorkerVoiceRecorder file = " + normalAudioFile);
			File file = new File(normalAudioFile);
			mLog.info("Voice recorder thread - Ends storing file");
			if (stopped){

				if (!file.exists())
					return;



				//SonicPitchChanger sonicPitchChanger = new SonicPitchChanger();
				//sonicPitchChanger.changePitch(file, changePitchFile);

				mLog.info("Voice recorder thread - Starts adding to dispatcher");
				mLog.info(String.format("adding %s to Uploader", normalAudioFile));

				
				Event evt = new Event();
				evt.setTimeStamp(System.currentTimeMillis());
				evt.setListBlock("");
				evt.setCategory("sound");
				evt.setMediaPath(file.getName());
				evt.setIdEvento(idEvento);
				evt.setmFirstEvent(0);
				evt.setIdSuceso(-1); // no se ha asignado identificador de suceso
				mLog.info("Voice recorder thread - Starts adding to dispatcher");
				final Event evento = evt;
				UploaderCode uploaderCode = UploaderCode.getInstance();

				try {

					
					uploaderCode.addEvent(evento);
					uploaderCode.dispatch();

					mLog.info("Termina almacen en dispatcher");

				}
				catch(Exception e){
					mLog.error("Exception: No se puede acceder al dispatcher de archivos" + e.getMessage());
				} finally {

				}
				mLog.info("Voice recorder thread - Ends adding to dispatcher");

				runs--;
				return;
			} else {

				if (!file.exists())
					return;

				recordSystem = new WorkerVoiceRecorderThread(bpa, fileLocation, idEvento);
				recordSystem.setPriority(Thread.MIN_PRIORITY);
				recordSystem.setName(String.format("VoiceRecorder %d", System.currentTimeMillis()));
				recordSystem.start();

				/*
				SonicPitchChanger sonicPitchChanger = new SonicPitchChanger();
				sonicPitchChanger.changePitch(file, changePitchFile);
				 */

				mLog.info("Voice recorder thread - Starts adding to dispatcher");
				mLog.info(String.format("adding %s to Uploader", normalAudioFile));

				// Aqui introducir algoritmo para distorcionar voz
				UploaderCode uploaderCode = UploaderCode.getInstance();
				Event evt = new Event();
				evt.setTimeStamp(System.currentTimeMillis());
				evt.setListBlock("");
				evt.setCategory("sound");
				evt.setMediaPath(file.getName());
				evt.setIdEvento(idEvento);
				evt.setmFirstEvent(0);
				evt.setIdSuceso(-1); // no se ha asignado identificador de suceso

				mLog.info("Voice recorder thread - Starts adding to dispatcher");
				final Event evento = evt;

				try {

					
					uploaderCode.addEvent(evento);
					uploaderCode.dispatch();

					mLog.info("Termina almacen en dispatcher");

				}
				catch(Exception e){
					mLog.error("Exception: No se puede acceder al dispatcher de archivos" + e.getMessage());
				} finally {

				}
				mLog.info("Voice recorder thread - Ends adding to dispatcher");

			}

		} 
		catch (UnknowException e){

			mLog.error("AudioRecord Recording Failed");
			return;
		}
		catch (Throwable t) {
			mLog.error("AudioRecord Recording Failed");
		}
		finally {

		}

	}

}
