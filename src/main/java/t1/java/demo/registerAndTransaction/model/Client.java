package t1.java.demo.registerAndTransaction.model;


    @Entity
    public class Client {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;


    }
}
