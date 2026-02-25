import java.util.Scanner;

class Node {
    String berita;
    Node next, prev;

    Node(String berita) {
        this.berita = berita;
        this.next = this.prev = null;
    }
}

class CircularDoublyLinkedList {
    Node head = null;
    int size = 0;

    // Insert di akhir
    public void insert(String berita) {
        Node newNode = new Node(berita);

        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;

            tail.next = newNode;
            newNode.prev = tail;

            newNode.next = head;
            head.prev = newNode;
        }
        size++;
        System.out.println("Berita berhasil ditambahkan.");
    }

    // Hapus berdasarkan nomor urut
    public void delete(int posisi) {
        if (head == null || posisi < 1 || posisi > size) {
            System.out.println("Posisi tidak valid.");
            return;
        }

        Node current = head;

        if (size == 1) {
            head = null;
        } else {
            for (int i = 1; i < posisi; i++) {
                current = current.next;
            }

            current.prev.next = current.next;
            current.next.prev = current.prev;

            if (current == head) {
                head = current.next;
            }
        }

        size--;
        System.out.println("Berita berhasil dihapus.");
    }

    // Tampilkan forward
    public void tampilForward() {
        if (head == null) {
            System.out.println("Tidak ada berita.");
            return;
        }

        Node current = head;
        int no = 1;

        do {
            System.out.println(no + ". " + current.berita);
            current = current.next;
            no++;
            try { Thread.sleep(3000); } catch (Exception e) {}
        } while (current != head);
    }

    // Tampilkan backward
    public void tampilBackward() {
        if (head == null) {
            System.out.println("Tidak ada berita.");
            return;
        }

        Node current = head.prev;
        int no = size;

        do {
            System.out.println(no + ". " + current.berita);
            current = current.prev;
            no--;
            try { Thread.sleep(3000); } catch (Exception e) {}
        } while (current != head.prev);
    }

    // Tampilkan berita tertentu
    public void tampilTertentu(int posisi) {
        if (head == null || posisi < 1 || posisi > size) {
            System.out.println("Posisi tidak valid.");
            return;
        }

        Node current = head;
        for (int i = 1; i < posisi; i++) {
            current = current.next;
        }

        System.out.println("Berita ke-" + posisi + ": " + current.berita);
    }
}

public class NewsCDLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        int pilih;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Insert berita");
            System.out.println("2. Hapus berita");
            System.out.println("3. Tampilkan forward");
            System.out.println("4. Tampilkan backward");
            System.out.println("5. Tampil berita tertentu");
            System.out.println("6. Exit");
            System.out.print("Pilih: ");
            pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    System.out.print("Masukkan berita: ");
                    String berita = sc.nextLine();
                    list.insert(berita);
                    break;

                case 2:
                    System.out.print("Masukkan nomor urut: ");
                    int hapus = sc.nextInt();
                    list.delete(hapus);
                    break;

                case 3:
                    list.tampilForward();
                    break;

                case 4:
                    list.tampilBackward();
                    break;

                case 5:
                    System.out.print("Masukkan nomor urut: ");
                    int lihat = sc.nextInt();
                    list.tampilTertentu(lihat);
                    break;
            }
        } while (pilih != 6);

        System.out.println("Program selesai.");
    }
}