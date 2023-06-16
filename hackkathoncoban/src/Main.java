import config.Config;
import controller.BookController;
import modal.Book;

public class Main {
    static BookController bookController = new BookController();

    public static void main(String[] args) {
        int choose;
        while (true) {
            System.out.println("****************JAVA-HACKATHON-05-BASIC-MENU***************\n" +
                    "1. Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách [15 điểm]\n" +
                    "2. Hiển thị thông tin tất cả sách trong thư viện [15 điểm]\n" +
                    "3. Sắp xếp sách theo lợi nhuận tăng dần [15 điểm]\n" +
                    "4. Xóa sách theo mã sách [10 điểm]\n" +
                    "5. Tìm kiếm tương đối sách theo tên sách hoặc mô tả [10 điểm]\n" +
                    "6. Thay đổi thông tin sách theo mã sách [10 điểm]\n" +
                    "7. Thoát [05 điểm]");
            choose = Config.scanner().nextInt();
            switch (choose) {
                case 1:
                    // nhập vào thông tin sách
                    addNewBook();
                    break;
                case 2:
                    // hiển thị sách có trong thư viện
                    showListBook();
                    break;
                case 3:

                    break;
                case 4:
                    // xóa sách khỏi thư viện
                    deleteBook();
                    break;
                case 5:

                case 6:
                    // thay đổi thông tin sách
                    updateBook();
                    break;
                case 7:
                    // out progress
                    System.out.println("~~ Cảm ơn bạn đã sử dụng chương trình ~~");
                    System.exit(0);
                    break;
                default:
                    System.err.print("Vui lòng nhập lại (1 -> 7): ");
                    break;
            }
        }
    }

    // add new book to library
    public static void addNewBook() {
        System.out.print("Nhập vào số lượng bạn muốn nhập: ");
        int n = Config.scanner().nextInt();
        Config.scanner().nextLine(); // Consume newline character
        for (int i = 0; i < n; i++) {
            System.out.println("Sản phẩm thứ " + (i + 1));
            Book book = new Book();
            book.setBookId(bookController.getNewId());

            // check name
            while (true) {
                System.out.print("Nhập tên sách: ");
                String name = Config.scanner().nextLine();
                if (name.trim().isEmpty()) {
                    System.err.println("Tên sách không được để trống. Vui lòng nhập lại.");
                } else {
                    book.setBookName(name);
                    break;
                }
            }

            // check author
            while (true) {
                System.out.print("Nhập tên tác giả: ");
                String author = Config.scanner().nextLine();
                if (author.trim().isEmpty()) {
                    System.err.println("Tên tác giả không được để trống. Vui lòng nhập lại.");
                } else {
                    book.setAuthor(author);
                    break;
                }
            }

            // check description
            while (true) {
                System.out.print("Nhập mô tả sách: ");
                String description = Config.scanner().nextLine();
                if (description.trim().isEmpty()) {
                    System.err.println("Mô tả sách không được để trống. Vui lòng nhập lại.");
                } else {
                    book.setDescription(description);
                    break;
                }
            }

            // check importPrice
            while (true) {
                System.out.print("Nhập giá nhập: ");
                double importPrice = Config.scanner().nextDouble();
                if (importPrice <= 0) {
                    System.err.println("Giá nhập phải là một số dương. Vui lòng nhập lại.");
                } else {
                    book.setImportPrice(importPrice);
                    break;
                }
            }

            // check exportPrice
            while (true) {
                System.out.print("Nhập giá xuất: ");
                double exportPrice = Config.scanner().nextDouble();
                if (exportPrice <= 0) {
                    System.err.println("Giá xuất phải là một số dương. Vui lòng nhập lại.");
                } else {
                    book.setExportPrice(exportPrice);
                    break;
                }
            }

            bookController.save(book);
        }
    }

    // show all books in the library
    public static void showListBook() {
        if (bookController.getSize() == 0) {
            System.err.println("~~ Thư viện không có sách nào ~~");
            return;
        }
        for (Book book : bookController.getAll()) {
            if (book != null) {
                System.out.println(book);
            }
        }
        System.out.println("--------------------------------");
    }



    // delete a book from the library
    public static void deleteBook() {
        System.out.print("Nhập mã sách bạn muốn xóa: ");
        int id = Config.scanner().nextInt();
        if (bookController.delete(id)) {
            System.out.println("~~ Sách có mã " + id + " đã được xóa khỏi thư viện ~~");
        } else {
            System.err.println("~~ Không tìm thấy sách có mã " + id + " trong thư viện ~~");
        }
    }


    // update book
    public static void updateBook() {
        System.out.print("Nhập mã sách bạn muốn sửa: ");
        int id = Config.scanner().nextInt();
        Config.scanner().nextLine(); // Consume newline character
        Book book = bookController.findById(id);
        if (book == null) {
            System.err.println("~~ Không tìm thấy sách có mã " + id + " trong thư viện ~~");
            return;
        }
        // sửa tên sách
        while (true) {
            System.out.printf("Sửa tên sách (tên cũ: %s): ", book.getBookName());
            String name = Config.scanner().nextLine();
            if (name.trim().isEmpty()) {
                System.err.println("Tên sách không được để trống. Vui lòng nhập lại.");
            } else {
                book.setBookName(name);
                break;
            }
        }
        // sửa tên tác giả
        while (true) {
            System.out.printf("Sửa tên tác giả (tên cũ: %s): ", book.getAuthor());
            String author = Config.scanner().nextLine();
            if (author.trim().isEmpty()) {
                System.err.println("Tên tác giả không được để trống. Vui lòng nhập lại.");
            } else {
                book.setAuthor(author);
                break;
            }
        }
        // sửa mô tả sách
        while (true) {
            System.out.printf("Sửa mô tả sách (mô tả cũ: %s): ", book.getDescription());
            String description = Config.scanner().nextLine();
            if (description.trim().isEmpty()) {
                System.err.println("Mô tả sách không được để trống. Vui lòng nhập lại.");
            } else {
                book.setDescription(description);
                break;
            }
        }
        // sửa giá nhập
        while (true) {
            System.out.printf("Sửa giá nhập (giá cũ: %f): ", book.getImportPrice());
            double importPrice = Config.scanner().nextDouble();
            if (importPrice <= 0) {
                System.err.println("Giá nhập phải là một số dương. Vui lòng nhập lại.");
            } else {
                book.setImportPrice(importPrice);
                break;
            }
        }
        // sửa giá xuất
        while (true) {
            System.out.printf("Sửa giá xuất (giá cũ: %f): ", book.getExportPrice());
            double exportPrice = Config.scanner().nextDouble();
            if (exportPrice <= 0) {
                System.err.println("Giá xuất phải là một số dương. Vui lòng nhập lại.");
            } else {
                book.setExportPrice(exportPrice);
                break;
            }
        }
        book.setInterest(book.getImportPrice(), book.getExportPrice());
        bookController.save(book);
        System.out.println("~~ Sách có mã " + id + " đã được cập nhật ~~");
    }
}
