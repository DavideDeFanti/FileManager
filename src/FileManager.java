import java.io.*;

public class FileManager {

    private String basePath = "src/Archivio/";

    // Metodo per scrivere su file
    public void scriviSuFile(String nomeFile, String contenuto) {
        String filePath = basePath + nomeFile;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(contenuto);
            System.out.println("Scrittura completata con successo");
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
        }

    }

    // Metodo per leggere su file
    public void leggiDaFile(String nomeFile) {
        String filePath = basePath + nomeFile;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String riga;
            while ((riga = reader.readLine()) != null) {
                System.out.println(riga);
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }

    //Utilizzare il metodo leggiDaFile() invece di questo metodo
    @Deprecated
    public void stampaFile(String nomeFile) {
        leggiDaFile(nomeFile);
        System.out.println("Utilizzare il metodo leggiDaFile() invece di questo metodo");
    }

    // Metodo per copiare un file
    public void copiaFile(String sorgente, String destinazione) {
        String filePath = basePath + sorgente;
        String copyFilePath = basePath + destinazione;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copyFilePath))) {
            byte[] buffer = new byte[1024];
            int byteRead;
            while((byteRead = bis.read(buffer)) != -1){
                bos.write(buffer,0,byteRead);
            }
            System.out.println("File copiato correttamente");

        } catch (IOException e) {
            System.out.println("Errore durante la copia del file: " + e.getMessage());
        }
    }

    // Metodo per eliminare i file
    public void eliminaFile(String nomeFile){
        String filePath = basePath + nomeFile;
        File file = new File(filePath);
        if (file.exists()){
            if (file.delete()){
                System.out.println("File eliminato correttamente");
            }else {
                System.out.println("Impossibile eliminare il file: errore durante l'eliminazione");
            }

        }else {
            System.out.println("Impossibile eliminare il file: file non trovato");
        }

    }

}