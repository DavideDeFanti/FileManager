import java.io.*;

public class Documento implements Serializable {

    private static String basePath = "C:\\Users\\ddefanti\\OneDrive - MANUTENCOOP Facility Management S.p.A\\Desktop\\DDF\\Projects\\ESERCIZI\\FileManager\\src\\Archivio\\";
    private static final long serialVersionUID = 1L;
    private String titolo;
    private String autore;
    private String contenuto;
    private int numeroPagine;

    public Documento(String titolo, String autore, String contenuto, int numeroPagine) {
        this.titolo = titolo;
        this.autore = autore;
        this.contenuto = contenuto;
        this.numeroPagine = numeroPagine;
    }

    @Override
    public String toString() {
        return "Titolo documento: " + titolo + " Autore: " + autore + " Numero pagine: " + numeroPagine + " Contenuto: " + contenuto;
    }

    // Metodo per salvare un File
    public void salvaSuFile(String nomeFile){
        String filePath = basePath + nomeFile;

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(this);
            System.out.println("Documento salvato con successo");
        }catch (IOException e){
            System.out.println("Errore durante il salvataggio del documento: " + e.getMessage());
        }
    }

    // Metodo per caricare un File
    public static Documento caricaDaFile(String nomeFile){
        String filePath = basePath + nomeFile;
        Documento doc;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            doc = (Documento) ois.readObject();
            System.out.println("Documento caricato con successo ");
            return doc;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore durante il caricamento del documento: " + e.getMessage());
        }
        return null;
    }
}