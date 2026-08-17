package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Client> clients = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Aquí irá el menú (Fase 8)
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
}