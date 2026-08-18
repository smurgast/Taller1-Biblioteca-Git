package com.mycompany.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Loan> loans = new ArrayList<>();
    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<Client> clients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            showMainMenu();
            System.out.print("Selecciona una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> clientsMenu();
                case 2 -> booksMenu();
                case 3 -> loansMenu();
                case 0 -> System.out.println("Saliendo del sistema... Hasta pronto.");
                default -> System.out.println("Opcion invalida, intenta de nuevo.");
            }
        } while (option != 0);
        sc.close();
    }

    private static void showMainMenu() {
        System.out.println("\n===== BIBLIOTECA MUNICIPAL DE VALLEDUPAR =====");
        System.out.println("1. Gestion de Clientes");
        System.out.println("2. Gestion de Libros");
        System.out.println("3. Gestion de Prestamos");
        System.out.println("0. Salir");
    }

    private static void clientsMenu() {
        int option;
        do {
            System.out.println("\n--- GESTION DE CLIENTES ---");
            System.out.println("1. Crear cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar cliente por id");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver al menu principal");
            System.out.print("Selecciona una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> createClient();
                case 2 -> listClients();
                case 3 -> searchClientMenu();
                case 4 -> updateClient();
                case 5 -> deleteClient();
                case 0 -> {}
                default -> System.out.println("Opcion invalida, intenta de nuevo.");
            }
        } while (option != 0);
    }

    private static void booksMenu() {
        int option;
        do {
            System.out.println("\n--- GESTION DE LIBROS ---");
            System.out.println("1. Crear libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Buscar libro por codigo");
            System.out.println("4. Actualizar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("0. Volver al menu principal");
            System.out.print("Selecciona una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> createBook();
                case 2 -> listBooks();
                case 3 -> searchBookMenu();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                case 0 -> {}
                default -> System.out.println("Opcion invalida, intenta de nuevo.");
            }
        } while (option != 0);
    }

    private static void loansMenu() {
        int option;
        do {
            System.out.println("\n--- GESTION DE PRESTAMOS ---");
            System.out.println("1. Registrar prestamo");
            System.out.println("2. Registrar devolucion");
            System.out.println("3. Listar prestamos activos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Selecciona una opcion: ");
            option = readInt();
            switch (option) {
                case 1 -> createLoan();
                case 2 -> returnLoan();
                case 3 -> listActiveLoans();
                case 0 -> {}
                default -> System.out.println("Opcion invalida, intenta de nuevo.");
            }
        } while (option != 0);
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Ingresa un numero valido: ");
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static void createClient() {
        System.out.println("\n-- Crear cliente --");
        System.out.print("Id: ");
        String id = sc.nextLine().trim();
        if (findClientById(id) != null) {
            System.out.println("Ya existe un cliente con ese id.");
            return;
        }
        System.out.print("Nombre: ");
        String name = sc.nextLine().trim();
        System.out.print("Telefono: ");
        String phone = sc.nextLine().trim();
        System.out.print("Email: ");
        String email = sc.nextLine().trim();
        clients.add(new Client(id, name, phone, email));
        System.out.println("Cliente creado con exito.");
    }

    private static Client findClientById(String id) {
        for (Client c : clients) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
    private static void listClients() {
        System.out.println("\n-- Listado de clientes --");
        if (clients.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        for (Client c : clients) {
            System.out.println(c);
        }
    }
    private static void searchClientMenu() {
        System.out.print("Ingresa el id del cliente a buscar: ");
        String id = sc.nextLine().trim();
        Client c = findClientById(id);
        if (c == null) {
            System.out.println("No se encontro un cliente con ese id.");
        } else {
            System.out.println("Cliente encontrado: " + c);
        }
    }

    private static void updateClient() {
        System.out.print("Ingresa el id del cliente a actualizar: ");
        String id = sc.nextLine().trim();
        Client c = findClientById(id);
        if (c == null) {
            System.out.println("No se encontro un cliente con ese id.");
            return;
        }
        System.out.print("Nuevo nombre (" + c.getName() + "): ");
        String name = sc.nextLine().trim();
        System.out.print("Nuevo telefono (" + c.getPhone() + "): ");
        String phone = sc.nextLine().trim();
        System.out.print("Nuevo email (" + c.getEmail() + "): ");
        String email = sc.nextLine().trim();
        if (!name.isBlank()) c.setName(name);
        if (!phone.isBlank()) c.setPhone(phone);
        if (!email.isBlank()) c.setEmail(email);
        System.out.println("Cliente actualizado con exito.");
    }

    private static void deleteClient() {
        System.out.print("Ingresa el id del cliente a eliminar: ");
        String id = sc.nextLine().trim();
        Client c = findClientById(id);
        if (c == null) {
            System.out.println("No se encontro un cliente con ese id.");
            return;
        }
        clients.remove(c);
        System.out.println("Cliente eliminado con exito.");
    }
    private static void createBook() {
        System.out.println("\n-- Crear libro --");
        System.out.print("Codigo: ");
        String code = sc.nextLine().trim();
        if (findBookByCode(code) != null) {
            System.out.println("Ya existe un libro con ese codigo.");
            return;
        }
        System.out.print("Titulo: ");
        String title = sc.nextLine().trim();
        System.out.print("Anio de publicacion: ");
        String year = sc.nextLine().trim();
        System.out.print("Autor: ");
        String author = sc.nextLine().trim();
        books.add(new Book(code, title, year, author, true));
        System.out.println("Libro creado con exito.");
    }

    private static Book findBookByCode(String code) {
        for (Book b : books) {
            if (b.getCode().equalsIgnoreCase(code)) {
                return b;
            }
        }
        return null;
    }

    private static void listBooks() {
        System.out.println("\n-- Listado de libros --");
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void searchBookMenu() {
        System.out.print("Ingresa el codigo del libro a buscar: ");
        String code = sc.nextLine().trim();
        Book b = findBookByCode(code);
        if (b == null) {
            System.out.println("No se encontro un libro con ese codigo.");
        } else {
            System.out.println("Libro encontrado: " + b);
        }
    }

    private static void updateBook() {
        System.out.print("Ingresa el codigo del libro a actualizar: ");
        String code = sc.nextLine().trim();
        Book b = findBookByCode(code);
        if (b == null) {
            System.out.println("No se encontro un libro con ese codigo.");
            return;
        }
        System.out.print("Nuevo titulo (" + b.getTitle() + "): ");
        String title = sc.nextLine().trim();
        System.out.print("Nuevo anio de publicacion (" + b.getPublicationYear() + "): ");
        String year = sc.nextLine().trim();
        System.out.print("Nuevo autor (" + b.getAuthor() + "): ");
        String author = sc.nextLine().trim();
        if (!title.isBlank()) b.setTitle(title);
        if (!year.isBlank()) b.setPublicationYear(year);
        if (!author.isBlank()) b.setAuthor(author);
        System.out.println("Libro actualizado con exito.");
    }

    private static void deleteBook() {
        System.out.print("Ingresa el codigo del libro a eliminar: ");
        String code = sc.nextLine().trim();
        Book b = findBookByCode(code);
        if (b == null) {
            System.out.println("No se encontro un libro con ese codigo.");
            return;
        }
        books.remove(b);
        System.out.println("Libro eliminado con exito.");
    }

    private static void createLoan() {
        System.out.println("\n-- Registrar prestamo --");
        System.out.print("Id del cliente: ");
        Client client = findClientById(sc.nextLine().trim());
        if (client == null) {
            System.out.println("No se encontro un cliente con ese id.");
            return;
        }
        System.out.print("Codigo del libro: ");
        Book book = findBookByCode(sc.nextLine().trim());
        if (book == null) {
            System.out.println("No se encontro un libro con ese codigo.");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("El libro no esta disponible actualmente.");
            return;
        }
        System.out.print("Id del prestamo: ");
        String loanId = sc.nextLine().trim();
        loans.add(new Loan(loanId, client, book, LocalDate.now(), "ACTIVO"));
        book.setAvailable(false);
        System.out.println("Prestamo registrado con exito.");
    }

    private static Loan findLoanById(String loanId) {
        for (Loan l : loans) {
            if (l.getLoanId().equalsIgnoreCase(loanId)) {
                return l;
            }
        }
        return null;
    }

    private static void returnLoan() {
        System.out.println("\n-- Registrar devolucion --");
        System.out.print("Id del prestamo: ");
        Loan loan = findLoanById(sc.nextLine().trim());
        if (loan == null) {
            System.out.println("No se encontro un prestamo con ese id.");
            return;
        }
        if ("DEVUELTO".equalsIgnoreCase(loan.getStatus())) {
            System.out.println("Ese prestamo ya fue devuelto.");
            return;
        }
        loan.setStatus("DEVUELTO");
        loan.getBook().setAvailable(true);
        System.out.println("Devolucion registrada con exito.");
    }

    private static void listActiveLoans() {
        System.out.println("\n-- Prestamos activos --");
        boolean found = false;
        for (Loan l : loans) {
            if ("ACTIVO".equalsIgnoreCase(l.getStatus())) {
                System.out.println(l);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hay prestamos activos.");
        }
    }

}