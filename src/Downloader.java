public class Downloader extends Thread {

    private String nomeFile;

    public Downloader(String nomeFile){
        this.nomeFile = nomeFile;
    }

    @Override
    public void run() {
        FileManager fileManager = new FileManager();
        System.out.println("Download " + Thread.currentThread().getName() + " avviato");
        fileManager.scriviSuFile(nomeFile,"Download completato!");
        System.out.println("Download " + Thread.currentThread().getName() + " completato");
    }
}