package com.iapp.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.iapp.utils.csvreader.CsvReader;

public class FileHelper {
	private Context context;
	private boolean hasSD = false;
	private String SDPATH;
	private String FILESPATH;

	public FileHelper(Context context) {
		this.context = context;
		hasSD = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		SDPATH = Environment.getExternalStorageDirectory().getPath();
		FILESPATH = this.context.getFilesDir().getPath();
	}

	public File createSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + "//" + fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}

	public boolean deleteSDFile(String fileName) {
		File file = new File(SDPATH + "//" + fileName);
		if (file == null || !file.exists() || file.isDirectory())
			return false;
		return file.delete();
	}

	public void writeSDFile(String str, String fileName) {
		FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(SDPATH + "//" + fileName, true);
			OutputStreamWriter writer = new OutputStreamWriter(outStream,
					"utf-8");
			writer.write(str);
			writer.close();
			outStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String readSDFile(String fileName) {
		StringBuffer sb = new StringBuffer();
		try {
			File f = new File(SDPATH + "//" + fileName);// 这是对应文件名
			InputStream in = new BufferedInputStream(new FileInputStream(f));
			BufferedReader br = new BufferedReader(new InputStreamReader(in,
					"utf-8"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				// 在这对tmp操作
				sb.append(tmp);
			}
			in.close();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		return sb.toString();
	}

	public String getFILESPATH() {
		return FILESPATH;
	}

	public String getSDPATH() {
		return SDPATH;
	}

	public boolean hasSD() {
		return hasSD;
	}

	/**
	 * @purpose read csv
	 * @param context
	 * @param filePath
	 * @return
	 */
	public static final List<String[]> readCsv(Context context, String filePath) {
		List<String[]> questionList = new ArrayList<String[]>();
		AssetManager assetManager = context.getAssets();

		try {
			InputStream csvStream = assetManager.open(filePath);
			InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
			CsvReader csvReader = new CsvReader(csvStreamReader);
			String[] line;

			// throw away the header

			while (csvReader.readRecord()) {
				line = csvReader.getValues();
				questionList.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return questionList;
	}

}
