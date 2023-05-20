package example01;

public class GarbageCollectionExample {
    public static void main(String[] args) {
        // 객체 생성
        MyClass obj1 = new MyClass();
        MyClass obj2 = new MyClass();

        // obj1을 obj2가 참조하도록 설정
        obj2.setReference(obj1);

        // obj1을 null로 설정하여 obj1에 대한 참조 제거
        obj1 = null;

        // 가비지 컬렉션 실행 요청
        System.gc();

        // 잠시 대기 (가비지 컬렉션 완료를 확인하기 위해)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // obj2가 obj1을 참조하고 있었지만 obj1은 가비지 컬렉션에 의해 정리됨
        // obj2는 obj1을 참조할 수 없으므로 null을 출력
        System.out.println("obj2의 참조: " + obj2.getReference());
    }
}