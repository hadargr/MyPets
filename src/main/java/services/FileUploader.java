/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

public class FileUploader {

    private final int limit_max_size = 10240000;
    private final String limit_type_file = "gif|jpg|png|jpeg";
    private final String path_to = "resources" + File.separator + "images";
    private String error = null;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String upload(Part fileUpload) {
        String fileSaveData = "";
        setError(null);
        try {
            if (fileUpload.getSize() > 0) {
                String submittedFileName = getFilename(fileUpload);
                if (checkFileType(submittedFileName)) {
                    if (fileUpload.getSize() > this.limit_max_size) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "File size too large!", ""));
                    } else {
                        String currentFileName = submittedFileName;
                        String extension = currentFileName.substring(currentFileName.lastIndexOf("."), currentFileName.length());
                        String pureFileName = currentFileName.substring(0, currentFileName.lastIndexOf("."));
                        Long nameRadom = Calendar.getInstance().getTimeInMillis();
                        fileSaveData = pureFileName + "_" + nameRadom + extension;
                        String fileSavePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + File.separator + this.path_to;
                        try {
                            byte[] fileContent = new byte[(int) fileUpload.getSize()];
                            InputStream in = fileUpload.getInputStream();
                            in.read(fileContent);

                            File fileToCreate = new File(fileSavePath, fileSaveData);
                            File folder = new File(fileSavePath);
                            if (!folder.exists()) {
                                folder.mkdirs();
                            }
                            try (FileOutputStream fileOutStream = new FileOutputStream(fileToCreate)) {
                                fileOutStream.write(fileContent);
                                fileOutStream.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("In catch #1");
                            setError("In catch #1");
                            fileSaveData = "";
                        }
                    }
                } else {
                    fileSaveData = "";
                    setError("File type is invalid");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("In catch #2");
            setError("In catch #2");
            fileSaveData = "";
        }
        return fileSaveData;
    }

    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    private boolean checkFileType(String fileName) {
        if (fileName.length() > 0) {
            String[] parts = fileName.split("\\.");
            if (parts.length > 0) {
                String extention = parts[parts.length - 1];
                return this.limit_type_file.contains(extention);
            }
        }
        return false;
    }

}
