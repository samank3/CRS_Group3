package enamel;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

public class AudioRecorder {

	String fileName;
	long recordTime;

	// public static void main(String[] args) throws LineUnavailableException,
	// InterruptedException {
	// AudioRecorder recorder = new AudioRecorder("tester",5000);
	// recorder.start();
	// }

	public AudioRecorder(String fileName, long recordTime) {
		this.fileName = fileName + ".wav";
		this.recordTime = recordTime;

	}

	public String start() throws LineUnavailableException, InterruptedException {
		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

		if (!AudioSystem.isLineSupported(info)) {
			return "No Microphone Detected.";
		}

		TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
		targetDataLine.open();

		Logger.getGlobal().info("Start Recording");
		targetDataLine.start();

		Thread stopper = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				AudioInputStream audioStream = new AudioInputStream(targetDataLine);

				// File wavFile = new File("FactoryScenarios/AudioFiles/" + fileName);
				File wavFile = new File(fileName);
				try {
					AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, wavFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		stopper.start();

		Thread.sleep(recordTime);

		targetDataLine.stop();
		targetDataLine.close();

		JOptionPane.showMessageDialog(null, "Recording Complete");
		Logger.getGlobal().info("Recording Complete");

		return "";

	}

}
