package example01;

class MyClass {
    private MyClass reference;

    public void setReference(MyClass reference) {
        this.reference = reference;
    }

    public MyClass getReference() {
        return reference;
    }

    // 객체가 제거될 때 호출되는 메소드
    @Override
    protected void finalize() throws Throwable {
        System.out.println("객체가 가비지 컬렉션에 의해 정리됨");
        super.finalize();
    }
}