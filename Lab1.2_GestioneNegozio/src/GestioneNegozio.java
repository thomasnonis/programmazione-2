public class GestioneNegozio {
    static public void main(String args[]){
        Prodotto scaffale[] = new Prodotto[6];

        scaffale[0] = new Alimentare("001", "Pane", 1,10);
        scaffale[1] = new Alimentare("002", "Latte", 5,20);
        scaffale[2] = new Alimentare("003", "Formaggio", 26,30);
        scaffale[3] = new NonAlimentare("004", "Tovaglioli", "Carta", 40);
        scaffale[4] = new NonAlimentare("005", "Bicchieri", "Plastica" ,50);
        scaffale[5] = new NonAlimentare("006", "Tagliere", "Legno" ,60);

        for (Prodotto p : scaffale) {
            System.out.println(p);
        }

        Prodotto.discount = 0.1;

        System.out.println("----------------------------");

        for (Prodotto p : scaffale) {
            System.out.println(p);
        }


    }
}
