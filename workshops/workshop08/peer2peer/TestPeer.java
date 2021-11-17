package peer2peer;

public class TestPeer {

    public static void main(String[] args) {
        Peer[] peers = {
                new Peer("124.203.203.101"),
                new Peer("124.121.201.222"),
                new Peer("124.111.100.100")
        };

        for(int i = 0; i < peers.length; ++i) {
            final int idx = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    peers[idx].connecter(peers[(idx+1)%peers.length]);
                }
            }).start();
        }
    }
}
