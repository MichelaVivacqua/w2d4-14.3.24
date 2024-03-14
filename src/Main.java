import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Product laDivinaCommedia = new Product(123456, "La Divina Commedia", "Books", 100.50);
        Product odissea = new Product(789123, "L'Odissea", "Books", 85.20);
        Product eneide = new Product(255284, "L'Eneide'", "Books", 105.45);
        Product iMalavoglia = new Product(745256, "I malavoglia", "Books", 87.60);

        Product culla = new Product(541568, "Culla", "Baby", 250.32);
        Product setDiBavette = new Product(741569, "Set di bavette", "Baby", 12.40);
        Product seggiolone = new Product(6541658, "Seggiolone", "Baby", 84.90);

        Product pallone = new Product(741256, "Pallone", "Boys", 15.90);
        Product racchetta = new Product(616584, "Racchetta", "Boys", 78.40);
        Product cappello = new Product(259114, "Cappellino", "Boys", 415.21);

        List<Product> productsList = new ArrayList<>();

        productsList.add(laDivinaCommedia);
        productsList.add(odissea);
        productsList.add(eneide);
        productsList.add(iMalavoglia);
        productsList.add(culla);
        productsList.add(setDiBavette);
        productsList.add(seggiolone);
        productsList.add(pallone);
        productsList.add(racchetta);
        productsList.add(cappello);

        List<Product> listalibri100 = productsList.stream()
                .filter(product -> "Books".equals(product.getCategory())&& product.getPrice() > 100)
                .toList();

        List<Product> listababy = productsList.stream()
                .filter(product -> "Baby".equals(product.getCategory()))
                .toList();

        List<Product> boysProducts = productsList.stream()
                .filter(product -> "Boys".equals(product.getCategory()))
                .map(product -> {
                    double discountedPrice = product.getPrice() * 0.9;
                    return new Product(product.getId(), product.getName(), product.getCategory(), discountedPrice);
                })
                .toList();

        LocalDate date = LocalDate.parse("2021-02-02");
        LocalDate datee = LocalDate.parse("2021-03-02");
        LocalDate datafuorirange = LocalDate.parse("2024-03-13");

        Customer cliente = new Customer(613555,"Michela",4);
        Customer mariobros = new Customer(16885,"MarioBros",2);
        Customer luigibros = new Customer(56526,"LuigiBros",2);

        Order ilMioOrdine = new Order(14746958,"spedito", LocalDate.now(),LocalDate.now(),boysProducts,cliente);
        Order ordineMario = new Order(54586,"evaso",date,date,listababy,mariobros);
        Order ordineLuigi = new Order(45620,"evaso",datee,datee,listalibri100,luigibros);
        Order ordinePeach = new Order(452698,"evaso",datafuorirange,datafuorirange,boysProducts,luigibros);


        List<Order> orders = new ArrayList<>();
        orders.add(ilMioOrdine);
        orders.add(ordineMario);
        orders.add(ordineLuigi);
        orders.add(ordinePeach);

//        ESERCIZIO 1

        // Raggruppo gli ordini per cliente
        Map<Customer, List<Order>> ordersByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));

        // Stampo la mappa
        ordersByCustomer.forEach((customer, customerOrders) -> {
            System.out.println("Cliente: " + customer.getName());
            System.out.println("Ordini:");
            customerOrders.forEach(order -> System.out.println("   - " + order));

//            Collectors.groupingBy(Order::getCustomer) viene utilizzato per raggruppare gli ordini per cliente, utilizzando il metodo getCustomer() della classe Order come funzione di raggruppamento.
//                    La mappa risultante è di tipo Map<Customer, List<Order>>, dove la chiave è il cliente e il valore è una lista di ordini effettuati da quel cliente.
//                    Infine, viene utilizzato un forEach per iterare sulla mappa risultante e stampare i dettagli del cliente e i suoi ordini.

        });
        System.out.println("----------------------------------");

//        ESERCIZIO 2
        // Calcolo il totale delle vendite per ogni cliente
        Map<Customer, Double> salesByCustomer = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.summingDouble(Order::getTotalAmount)));

        // Stampo la mappa
        salesByCustomer.forEach((customer, totalSales) -> {
            System.out.println("Cliente: " + customer.getName());
            System.out.println("Importo totale degli acquisti: " + totalSales);
        });

//        Utilizziamo Collectors.groupingBy() per raggruppare gli ordini per cliente.
//        Usiamo Collectors.summingDouble() per calcolare il totale delle vendite per ogni cliente, utilizzando il metodo getTotalAmount() della classe Order per ottenere l'importo totale di ogni ordine.
//        La mappa risultante è di tipo Map<Customer, Double>, dove la chiave è il cliente e il valore è l'importo totale delle sue vendite.
//        Infine, utilizziamo un forEach per iterare sulla mappa risultante e stampare i dettagli del cliente e l'importo totale dei suoi acquisti.

        System.out.println("----------------------------------");

//        ESERCIZIO 3

        // Trovo il prodotto più costoso
        Optional<Product> prodottoPiuCostoso = productsList.stream()
                .max(Comparator.comparingDouble(Product::getPrice));

        // Verifico se il prodotto più costoso è presente e stampalo
        prodottoPiuCostoso.ifPresent(prodotto -> {
            System.out.println("Prodotto più costoso:");
            System.out.println(prodotto);
        });
//        max(Comparator.comparingDouble(Product::getPrice)) viene utilizzato per trovare il massimo tra gli elementi dell'elenco in base al prezzo del prodotto.
//        Optional<Product> viene utilizzato perché non è garantito che l'elenco dei prodotti sia non vuoto. Quindi, se il prodotto più costoso viene trovato, viene restituito come un oggetto Optional.
//        ifPresent() viene utilizzato per verificare se il prodotto più costoso è presente e, se presente, stamparlo.


        List<Product> prodottiPiuCostosi = productsList.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(3)
                .toList();

        // Stampa i tre prodotti più costosi
        System.out.println("I tre prodotti più costosi:");
        prodottiPiuCostosi.forEach(System.out::println);

//        sorted(Comparator.comparingDouble(Product::getPrice).reversed()) viene utilizzato per ordinare gli elementi in base al prezzo del prodotto in ordine decrescente.
//                limit(3) viene utilizzato per limitare il flusso agli elementi dei primi tre prodotti più costosi.
//        collect(Collectors.toList()) viene utilizzato per raccogliere i risultati in una lista.

        System.out.println("----------------------------------");

//        ESERCIZIO 4
        
    }
}
