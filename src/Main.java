import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(256);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        Transaction tx1 = new Transaction("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa", "1BvBMSEYstWetqTFn5Au4m4GFg7xJaNVN2", 50.0, privateKey);
        Transaction tx2 = new Transaction("bc1qxy2kgdygjrsqtzq2n0yrf2493p83kkfjhx0wlh", "1Bf9sZvBHPFGVPX71WX2njhd1NXKv5y7v5\n", 30.0, privateKey);

        Block block = new Block(1, "0", List.of(tx1, tx2), 2, privateKey);

        Blockchain blockchain = new Blockchain();
        blockchain.addBlock(block);

        System.out.println(blockchain);

        System.out.println("Transaction 1 signature valid: " + tx1.verifySignature(publicKey));
        System.out.println("Transaction 2 signature valid: " + tx2.verifySignature(publicKey));
        System.out.println("Block signature valid: " + block.verifyBlock(publicKey));
    }
}
