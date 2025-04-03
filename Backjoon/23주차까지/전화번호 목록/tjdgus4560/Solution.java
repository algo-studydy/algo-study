import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //트라이를 위한 노드
    public static class Node{
        Map<Character, Node> child;
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }

    public static class Trie {
        private Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word){
            Node cur = root;
            for(char c : word.toCharArray()){
                // putIfAbsent : c 라는 키값이 존재하면 c 의 value 리턴 아니면 키,값쌍 삽입
                cur.child.putIfAbsent(c, new Node());
                cur = cur.child.get(c);
            }

            cur.endOfWord = true;
        }

        // 삽입가능성체크
        public boolean isInsertOk(String s){
            Node cur = root;
            for(char c : s.toCharArray()){
                cur = cur.child.get(c);
                if(cur == null) break;
                if(cur.endOfWord){
                    // 이전에 해당 문자열로 끝나는게 존재하므로 삽입 불가능
                    return false;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        L :for(int T = 0; T<t; T++){
            Trie trie = new Trie();
            int n = Integer.parseInt(br.readLine());

            String[] arr = new String[n];
            for(int i=0; i<n; i++){
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);

            for(int i=0; i<n; i++){
                //입력가능체크
                if(!trie.isInsertOk(arr[i])){
                    System.out.println("NO");
                    continue L;
                }
                trie.insert(arr[i]);
            }

            System.out.println("YES");
        }
    }
}
