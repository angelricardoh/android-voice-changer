// This file was written by me, Bill Cox in 2011.
// I place this file into the public domain.  Feel free to copy from it.
// Note, however that libsonic, which this application links to,
// is licensed under LGPL.  You can link to it in your commercial application,
// but any changes you make to sonic.c or sonic.h need to be shared under
// the LGPL license.

package org.vinuxproject.sonic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SonicPitchChanger 
{

	static char[] chunkID = new char[4];
	static int chunksize = 0;
	static char[] format = new char[4];
	static char[] subchID1 = new char[4];
	static int subchunksize1 = 0;
	static short audfmt = 0;
	static short numchan = 0;
	static int samplerate = 0;
	static int byterate = 0;
	static short blockalign = 0;
	static short bitpersample = 0;
	static char[] data = new char[4];
	static int subchsize = 0;
	
	public SonicPitchChanger(){
		
	}

	public void start()
	{
		try {
			//fields of the wave file header;
			byte[] bdata = new byte[44];
			ByteBuffer bytebuf = ByteBuffer.wrap(bdata);

			File file = new File(Environment.getExternalStorageDirectory() + "/" + "poly.wav");
			FileInputStream fin = new FileInputStream(file);

			fin.read(bdata);
			//System.out.println(bdata);
			int i = 0;
			int k = 0;
			System.out.print("ChunkID :");
			for (i = 0; i < 4; i++) {
				chunkID[k] = (char) bdata[i];
				System.out.print(chunkID[k]);
				k++;
			}

			chunksize = bytebuf.getInt(i);
			System.out.println("\n" + i + "Chunksize :" + chunksize);
			i += 4;
			k = 0;
			System.out.print("\n" + i + "Foramt :");
			for (; k < 4; i++) {
				format[k] = (char) bdata[i];
				System.out.print(format[k]);
				k++;
			}
			k = 0;
			System.out.print("\n" + i + "subchID1 :");
			for (; k < 4; i++) {
				subchID1[k] = (char) bdata[i];
				System.out.print(subchID1[k]);
				k++;
			}

			subchunksize1 = bytebuf.getInt(i);
			System.out.println("\n" + i + "subchsize1" + subchunksize1);
			i += 4;
			audfmt = bytebuf.getShort(i);
			System.out.println("\n" + i + "AudFmt" + audfmt);
			i += 2;
			numchan = bytebuf.getShort(i);
			System.out.println(i + "Numchan" + numchan);
			i += 2;
			samplerate = bytebuf.getInt(i);
			System.out.println(i + "Samplerate" + samplerate);
			i += 4;
			byterate = bytebuf.getInt(i);
			System.out.println(i + "ByteRate" + byterate);
			i += 4;
			blockalign = bytebuf.getShort(i);
			System.out.println(i + "blockalign" + blockalign);
			i += 2;
			bitpersample = bytebuf.getShort(i);
			System.out.println(i + "bitpersample" + bitpersample);
			i += 2;
			k = 0;
			System.out.println(i + "data");
			for (; k < 4; i++) {
				data[k] = (char) bdata[i];
				System.out.print(data[k]);
				k++;
			}
			subchsize = bytebuf.getInt(i);
			System.out.println(i + "subchsize" + subchsize);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void start2()
	{
		try {
			//fields of the wave file header;
			byte[] bdata = new byte[44];
			ByteBuffer bytebuf = ByteBuffer.wrap(bdata);

			File file = new File(Environment.getExternalStorageDirectory() + "/" + "mysound1355165737818.wav");
			FileInputStream fin = new FileInputStream(file);

			fin.read(bdata);
			//System.out.println(bdata);
			int i = 0;
			int k = 0;
			System.out.print("ChunkID :");
			for (i = 0; i < 4; i++) {
				chunkID[k] = (char) bdata[i];
				System.out.print(chunkID[k]);
				k++;
			}

			chunksize = bytebuf.getInt(i);
			System.out.println("\n" + i + "Chunksize :" + chunksize);
			i += 4;
			k = 0;
			System.out.print("\n" + i + "Foramt :");
			for (; k < 4; i++) {
				format[k] = (char) bdata[i];
				System.out.print(format[k]);
				k++;
			}
			k = 0;
			System.out.print("\n" + i + "subchID1 :");
			for (; k < 4; i++) {
				subchID1[k] = (char) bdata[i];
				System.out.print(subchID1[k]);
				k++;
			}

			subchunksize1 = bytebuf.getInt(i);
			System.out.println("\n" + i + "subchsize1" + subchunksize1);
			i += 4;
			audfmt = bytebuf.getShort(i);
			System.out.println("\n" + i + "AudFmt" + audfmt);
			i += 2;
			numchan = bytebuf.getShort(i);
			System.out.println(i + "Numchan" + numchan);
			i += 2;
			samplerate = bytebuf.getInt(i);
			System.out.println(i + "Samplerate" + samplerate);
			i += 4;
			byterate = bytebuf.getInt(i);
			System.out.println(i + "ByteRate" + byterate);
			i += 4;
			blockalign = bytebuf.getShort(i);
			System.out.println(i + "blockalign" + blockalign);
			i += 2;
			bitpersample = bytebuf.getShort(i);
			System.out.println(i + "bitpersample" + bitpersample);
			i += 2;
			k = 0;
			System.out.println(i + "data");
			for (; k < 4; i++) {
				data[k] = (char) bdata[i];
				System.out.print(data[k]);
				k++;
			}
			subchsize = bytebuf.getInt(i);
			System.out.println(i + "subchsize" + subchsize);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void changePitch(File source, File changePitchFile)
	{

		float speed = 1.0f;
		float pitch = 1.2f;
		float rate = 1.0f;

		Sonic sonic = new Sonic(22050, 1);
		byte samples[] = new byte[409600];
		byte modifiedSamples[] = new byte[204800];
		byte headerSamples[] = new byte[409600];
		byte file[] = new byte[2000000];



		FileInputStream inf = null;
		try {
			inf = new FileInputStream(source.getPath());
		} catch (FileNotFoundException e1) {
			Log.i("algo","algo");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		FileOutputStream onf = null;
		try {
			onf = new FileOutputStream(changePitchFile.getPath());
		} catch (FileNotFoundException e1) {

			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		OutputStream soundFileout = new BufferedOutputStream(onf);

		InputStream soundFile = new BufferedInputStream(inf);

		int bytesRead;
		int totalBytes = 0;
		boolean first = true;
		try {
			if(soundFile != null) {
				sonic.setSpeed(speed);
				sonic.setPitch(pitch);
				sonic.setRate(rate);

				do {
					try {
						bytesRead = soundFile.read(samples, 0, samples.length);
						//headerFile.read(headerSamples, 0, 45);
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}
					if(bytesRead > 0) {
						sonic.putBytes(samples, bytesRead);
					} else {
						sonic.flush();
					}
					int available = sonic.availableBytes(); 
					if(available > 0) {
						if(modifiedSamples.length < available) {
							modifiedSamples = new byte[available*2];
						}

						sonic.receiveBytes(modifiedSamples, available);

						if (first)
						{
							int z = 0;
							for (z=0; z<45; z++)
								file[z] = samples[z];

							totalBytes += 45;

							for (int i= totalBytes, j =45 ; j<modifiedSamples.length; j++)
							{
								file[j] = modifiedSamples[j];
							}
							totalBytes += modifiedSamples.length;
							first = false;
							continue;
						}
						else{

							for (int i= totalBytes, j =0 ; j<modifiedSamples.length; j++)
							{
								file[j+totalBytes] = modifiedSamples[j];
							}
							totalBytes += modifiedSamples.length;
						}



					}
				} while(bytesRead > 0);

				soundFileout.write(file, 0, totalBytes);



			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				soundFileout.close();
				soundFile.close();

				onf.close();
				inf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}



}
