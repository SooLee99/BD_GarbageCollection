package example02;

import java.util.ArrayList;
import java.util.List;

/*
* 위의 예제에서는 MemoryLeakExample 클래스 내에 myList라는 정적 리스트를 선언하고,
* generateData() 메소드를 사용하여 매우 큰 데이터를 생성합니다.
* 그리고 generateData()로 생성된 데이터를 myList에 반복적으로 추가합니다.
*
* 주의할 점은 가비지 컬렉션이 myList에 추가된 객체들을 정리하지 않기 때문에 계속해서 메모리에 쌓이게 됩니다.
* 이렇게 되면 가비지 컬렉션에 의해 메모리가 자동으로 해제되지 않으며, 결국 메모리 누수가 발생합니다.
*
* 만약 메모리 누수를 방지하고자 한다면, main() 메소드 내에서 myList.clear()를 호출하여 가비지 컬렉션에 의해 메모리가 해제되도록 할 수 있습니다.
* 이를 통해 myList에 쌓인 객체들이 가비지 컬렉션에 의해 정리되어 메모리 누수가 방지됩니다.
* */

public class MemoryLeakExample {
    private static final List<String> myList = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            String data = generateData();
            myList.add(data);
        }

        // myList.clear();  // 가비지 컬렉션에 의한 메모리 해제를 위해 주석 해제
    }

    private static String generateData() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("data");
        }
        return sb.toString();
    }
}