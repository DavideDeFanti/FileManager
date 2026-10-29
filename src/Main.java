import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Fase 1: Creazione della classe FileManager (Gestione dei File)
//        FileManager fileManager = new FileManager();
//        fileManager.scriviSuFile("test.txt", "Testo di prova del primo file");
//        fileManager.copiaFile("test.txt", "copiaTest.txt");
//        fileManager.leggiDaFile("test.txt");
//        fileManager.eliminaFile("copiaTest.txt");
//
        // Fase 2: Serializzazione e Deserializzazione
//        Documento nuovoDoc = new Documento("Curriculm di Davide", "Davide", "Il mio curriculum", 2);
//        nuovoDoc.salvaSuFile("Cv.ser");
//        Documento docCaricato = nuovoDoc.caricaDaFile("Cv.ser");
//        System.out.println(docCaricato);
//
        // Fase 3: Creazione di Thread per Simulare il Download
        Downloader d1 = new Downloader("download1.txt");
        d1.setName("Download-1");
        Downloader d2 = new Downloader("download2.txt");
        d2.setName("Download-2");
        Downloader d3 = new Downloader("download3.txt");
        d3.setName("Download-3");
        d1.start();
        d2.start();
        d3.start();

        try {
            d1.join();
            d2.join();
            d3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Fase 4:Uso di Scanner per Input da Tastiera
        System.out.println("===== GESTIONE DOCUMENTI =====");
        System.out.println("1 - Inserisci documento");
        System.out.println("2 - Cerca documento");
        System.out.println("3 - Elimina documento");
        System.out.println("4 - Elenco documenti");
        System.out.println("0 - Esci");
        System.out.println("Digita un numero per selezionare l'azione");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Scelta: ");
        String sceltaUtente = scanner.nextLine();

        switch (sceltaUtente) {
            case "1":
                System.out.println("Inserimento documento:");
                System.out.println("Inserisci il titolo del documento");
                String titolo = scanner.nextLine();
                System.out.println("Inserisci l'autore del documento");
                String autore = scanner.nextLine();
                System.out.println("Inserisci il numero di pagine del documento");
                int numPagine = 0;
                boolean valoreValido = false;
                while (! valoreValido) {
                    try {
                        numPagine = scanner.nextInt();
                        scanner.nextLine();
                        valoreValido = true;
                    } catch (Exception e) {
                        System.out.println("Inserisci un numero intero");
                        scanner.nextLine();
                    }
                }
                System.out.println("Inserisci un contenuto testuale");
                String contenuto = scanner.nextLine();
                Documento nuovoDoc = new Documento(titolo, autore, contenuto, numPagine);
                nuovoDoc.salvaSuFile(titolo + ".ser");
                break;
            case "2":
                System.out.println("Ricerca documento:");
                System.out.print("Inserisci il titolo da cercare:");
                String titoloRicerca = scanner.nextLine();
                Documento doc = Documento.caricaDaFile(titoloRicerca + ".ser");
                if (doc != null) {
                    System.out.println(doc);
                } else {
                    System.out.println("Documento non trovato");
                }
                break;
            case "3":
                System.out.println("Elimina documento");
                System.out.print("Inserisci il titolo da eliminare:");
                String titoloDaEliminare = scanner.nextLine();
                FileManager fileManager = new FileManager();
                fileManager.eliminaFile(titoloDaEliminare + ".ser");
                break;
            case "4":
                System.out.println("Elenco documento");
                File cartella = new File("src/Archivio/");
                File[] documenti = cartella.listFiles();
                System.out.println("Elenco documenti:");
                for (File documento : documenti) {
                    System.out.println(documento.getName());
                }
                break;
            case "0":
                System.out.println("Uscita");
                break;
            default:
                System.out.println("Selezione un'opzione valida");
                break;

        }
        scanner.close();

    }
}