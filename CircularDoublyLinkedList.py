import time

class Node:
    def __init__(self, berita):
        self.berita = berita
        self.next = None
        self.prev = None


class CircularDoublyLinkedList:
    def __init__(self):
        self.head = None
        self.size = 0

    def insert(self, berita):
        new_node = Node(berita)

        if self.head is None:
            self.head = new_node
            new_node.next = new_node
            new_node.prev = new_node
        else:
            tail = self.head.prev

            tail.next = new_node
            new_node.prev = tail

            new_node.next = self.head
            self.head.prev = new_node

        self.size += 1
        print("Berita berhasil ditambahkan.")

    def delete(self, posisi):
        if self.head is None or posisi < 1 or posisi > self.size:
            print("Posisi tidak valid.")
            return

        current = self.head

        if self.size == 1:
            self.head = None
        else:
            for _ in range(1, posisi):
                current = current.next

            current.prev.next = current.next
            current.next.prev = current.prev

            if current == self.head:
                self.head = current.next

        self.size -= 1
        print("Berita berhasil dihapus.")

    def tampil_forward(self):
        if self.head is None:
            print("Tidak ada berita.")
            return

        current = self.head
        no = 1

        while True:
            print(f"{no}. {current.berita}")
            time.sleep(3)
            current = current.next
            no += 1
            if current == self.head:
                break

    def tampil_backward(self):
        if self.head is None:
            print("Tidak ada berita.")
            return

        current = self.head.prev
        no = self.size

        while True:
            print(f"{no}. {current.berita}")
            time.sleep(3)
            current = current.prev
            no -= 1
            if current == self.head.prev:
                break

    def tampil_tertentu(self, posisi):
        if self.head is None or posisi < 1 or posisi > self.size:
            print("Posisi tidak valid.")
            return

        current = self.head
        for _ in range(1, posisi):
            current = current.next

        print(f"Berita ke-{posisi}: {current.berita}")


def main():
    cdll = CircularDoublyLinkedList()

    while True:
        print("\n===== MENU =====")
        print("1. Insert berita")
        print("2. Hapus berita")
        print("3. Tampilkan forward")
        print("4. Tampilkan backward")
        print("5. Tampil berita tertentu")
        print("6. Exit")

        pilih = int(input("Pilih: "))

        if pilih == 1:
            berita = input("Masukkan berita: ")
            cdll.insert(berita)

        elif pilih == 2:
            hapus = int(input("Masukkan nomor urut: "))
            cdll.delete(hapus)

        elif pilih == 3:
            cdll.tampil_forward()

        elif pilih == 4:
            cdll.tampil_backward()

        elif pilih == 5:
            lihat = int(input("Masukkan nomor urut: "))
            cdll.tampil_tertentu(lihat)

        elif pilih == 6:
            print("Program selesai.")
            break


if __name__ == "__main__":
    main()