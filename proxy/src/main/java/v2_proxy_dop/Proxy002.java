package v2_proxy_dop;
//Дополнительные действия

public class Proxy002 {
    public static void main(String[] args) {
        Client client = new Client("#322");
        ServerProxy proxy = new ServerProxy();
        proxy.authentification(client);
        proxy.accessGranted(client);
        proxy.accessClosed(client);

    }
}

interface Server {
    void accessGranted(Client user);
    void accessClosed(Client user);
}

class Client {
    public String id;

    public Client(String id) {
        this.id = id;
    }
}

class MainServer implements Server {
    public MainServer() {
        System.out.println("Сервер создан");
    }

    @Override
    public void accessClosed(Client user) {
        System.out.println("Доступ закрыт пользователю с ID " + user.id);
    }

    @Override
    public void accessGranted(Client user) {
        System.out.println("Доступ предоставлен пользователю с ID " + user.id);
    }
}

/**
 * в данном прокси объекте мы будем проверять разрешение пользователя на доступ и уже
 * потом использовать вызовы методов исходного объекта
 */
class ServerProxy implements Server {
    private MainServer server;
    private Boolean flag = false;

    public ServerProxy() {
    }

    @Override
    public void accessGranted(Client client) {
        if (server == null || !this.flag) {
            System.out.println("Неизвестный пользователь");
        } else {
            server.accessGranted(client);
        }
    }

    public void authentification(Client client) {
        if (client.id != "#322") {
            return;
        }
        this.flag = true;
        System.out.println("Пользователь известен");
        server = new MainServer();
        accessGranted(client);
    }

    @Override
    public void accessClosed(Client client) {
        if (server == null) {
            System.out.println("Аутентификация не пройдена");
            return;
        }
        this.flag = true;
        server.accessClosed(client);
    }
}
