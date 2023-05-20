가비지 컬렉션(GarbageCollection)에 대해 공부해보고, 이에 대해 설명하는 저장소입니다. 밑의 내용은 자바를 기반으로 작성해보았습니다.
---
## 1. 가비지 컬렉션(GarbageCollection)이란?
- 프로그램이 동적으로 할당하는 메모리 중에서 더 이상 사용하지 않고, 필요 없는 부분을 알아서 탐지하고, 이를 자동으로 해제하여 관리해주는 기능을 말합니다.
- 이를 통해 프로그램의 메모리 누수를 방지하고, 시스템의 전체적인 안정성을 유지하는 데 중요한 역할을 합니다.
- 또한 개발자는 메모리 관리에 대한 직접적인 처리를 신경쓰지 않고도 프로그램을 작성할 수 있습니다.

- 자바에서의 가비지 컬렉션은 특히 중요합니다. 왜냐하면 자바는 개발자가 직접 메모리를 관리하는 대신, 가비지 컬렉터라는 시스템 구성 요소가 그 역할을 담당하기 때문입니다.
---
## 2. 가비지 컬렉션(GarbageCollection)의 사용 이유
(1) 메모리 누수 방지
- 개발자가 메모리를 직접 관리하려고 하면, 종종 '사용하지 않는' 메모리를 해제하는 것을 잊어버릴 수 있습니다. 이러한 상황은 메모리 누수를 초래하며, 이는 시간이 지남에 따라 점점 더 많은 메모리를 소비하고 결국에는 시스템 성능을 저하시킵니다. 가비지 컬렉션은 이러한 메모리 누수를 자동으로 방지합니다.   

(2) 메모리 단편화 방지
- 메모리 할당과 해제가 임의로 이루어질 때, 메모리 내에 사용할 수 없는 작은 공간들이 생겨날 수 있습니다. 이러한 현상을 메모리 단편화라고 합니다. 메모리 단편화는 큰 블록의 메모리를 요구하는 연산을 수행하려 할 때 문제를 일으킬 수 있습니다. 일부 가비지 컬렉션 알고리즘은 메모리를 정리하고 단편화를 방지하는 '압축' 단계를 포함합니다. 
  
(3) 개발 편의성 향상
- 가비지 컬렉션은 메모리 관리에 대한 부담을 줄여주므로, 개발자는 애플리케이션 로직에 더 집중할 수 있습니다. 이는 개발 과정을 단순화시키고, 코드의 가독성을 향상시키며, 실수를 줄여줍니다.   

(4) 프로그램의 안정성 향상
- 개발자가 메모리를 직접 관리할 때 발생할 수 있는 문제들(예: 더 이상 사용되지 않는 메모리를 해제하지 않는 문제, 이미 해제된 메모리를 참조하는 문제 등)로부터 애플리케이션의 안정성을 보장할 수 있습니다.
---
## 3. 자바에서 객체 생성과 사용
  (1) 객체 생성: 자바에서 새로운 객체를 생성하면, 해당 객체는 힙(Heap) 메모리 영역에 저장됩니다. 힙은 여러 섹션으로 나뉘며, 새로 생성된 객체는 보통 'Young Generation' 영역에 위치하게 됩니다.

  (2) 객체 사용: 프로그램이 실행되면서 이 객체를 사용합니다. 객체는 다른 객체를 참조할 수 있고, 더 이상 참조되지 않을 때 '가비지'로 간주됩니다.

  (3) Marking: 가비지 컬렉터는 힙에 있는 모든 객체를 검사하면서, 더 이상 참조되지 않는 객체를 찾습니다. 이런 객체를 'mark'하게 되는데, 이 단계에서는 해당 객체가 '가비지'임을 표시합니다. 이 과정은 'root'에서 시작해서 접근 가능한 모든 객체를 표시하게 됩니다. 여기서 'root'는 자바 스택에서의 지역 변수나 입력 매개변수, 네이티브 스택에서의 참조 등을 포함합니다.

  (4) Sweeping: 마킹 과정이 끝나면, 가비지 컬렉터는 표시된 객체를 힙에서 제거하고 메모리를 회수합니다. 이런 단계를 'sweeping'이라고 합니다. 이후 이 메모리 공간은 다시 사용 가능한 상태가 됩니다.

---
## 4. 가비지 컬렉션(GarbageCollection)의 문제점
- 자바 프로그램의 실행 중에 가비지 컬렉션을 실행하면 프로그램의 성능이 일시적으로 떨어질 수 있습니다. 이것을 가비지 컬렉션 '퍼즈'라고 부릅니다. 이러한 이유로, 실시간 시스템에서는 가비지 컬렉션을 제어해야 할 수도 있습니다.
---
## 5. 프로그래밍 언어의 종류별로 메모리 관리에 대한 접근 방식
- 자바(Java): 자바는 자동 메모리 관리를 지원하는 가비지 컬렉션을 사용합니다. 자바의 가비지 컬렉션은 힙 메모리 영역에 할당된 객체 중 더 이상 사용되지 않는 객체를 찾아서 메모리를 회수합니다. 가비지 컬렉션 알고리즘은 여러 가지가 있으며 (예: Serial, Parallel, CMS, G1 등), 자바 버전이나 JVM 옵션에 따라 선택할 수 있습니다.

- 파이썬(Python): 파이썬은 참조 카운팅 방식을 주로 사용하여 메모리를 관리합니다. 객체에 대한 참조가 0이 되면, 해당 객체는 메모리에서 제거됩니다. 하지만 이 방식만으로는 순환 참조(circular references)를 해결할 수 없기 때문에, 파이썬은 가비지 컬렉션도 함께 사용합니다. 파이썬의 가비지 컬렉터는 주로 순환 참조를 해결하기 위해 사용됩니다.

- C# / .NET: .NET 프레임워크와 C# 언어는 가비지 컬렉션을 사용하여 메모리를 관리합니다. .NET의 가비지 컬렉터는 객체가 힙에 할당되면 추적하고, 접근 불가능한 객체를 자동으로 제거합니다. .NET의 가비지 컬렉터는 Generational 가비지 컬렉션 전략을 사용하며, 객체가 오래 살아있을수록 메모리에서 제거될 확률이 줄어드는 것을 이용합니다.

- C/C++: C와 C++은 메모리 관리를 개발자에게 맡깁니다. 즉, 개발자는 malloc, calloc 등의 함수로 메모리를 할당하고, free나 delete를 이용해 메모리를 해제해야 합니다. 이 언어들은 가비지 컬렉션을 기본적으로 제공하지 않지만, 개발자가 필요에 따라 외부 라이브러리를 사용해 가비지 컬렉션을 구현할 수 있습니다.

- 자바스크립트(JavaScript): 자바스크립트 엔진은 가비지 컬렉션을 사용하여 메모리를 관리합니다. 대부분의 자바스크립트 엔진은 마크-앤-스위프(Mark-and-Sweep) 알고리즘을 사용합니다. 이는 더 이상 필요하지 않은 객체를 찾아내어 자동으로 메모리를 해제하는 방식입니다.
---
## 6. 가비지 컬렉션의 종류와 최적화 전략
- 가비지 컬렉션에는 다양한 알고리즘이 있습니다. 각 알고리즘은 자체적인 장단점과 최적화 전략을 가지고 있습니다. 몇 가지 대표적인 가비지 컬렉션 알고리즘으로는 세대별 가비지 컬렉션(Generational Garbage Collection), 병렬 가비지 컬렉션(Parallel Garbage Collection), CMS(Concurrent Mark and Sweep), G1(Garbage-First) 등이 있습니다. 이러한 알고리즘들은 메모리 사용 패턴, 애플리케이션의 특성, 시스템 리소스 등을 고려하여 선택되고 최적화될 수 있습니다.
---
## 7. 가비지 컬렉션의 튜닝과 최적화
- 가비지 컬렉션의 성능을 향상시키기 위해 튜닝과 최적화 작업을 수행할 수 있습니다. 이는 애플리케이션의 메모리 사용 패턴을 분석하고, 가비지 컬렉션의 동작을 조정하는 것을 의미합니다. 예를 들어, 가비지 컬렉션 히스토그램 분석을 통해 가비지 컬렉션 빈도와 시간을 조정하거나, 힙 크기를 조절하여 성능을 최적화할 수 있습니다. 또한, 가비지 컬렉션 로그 분석이나 프로파일링 도구를 사용하여 성능 이슈를 식별하고 개선할 수 있습니다.
---
## 8. 메모리 관리 팁과 가비지 컬렉션의 영향 최소화
- 가비지 컬렉션의 동작을 최적화하기 위해 몇 가지 메모리 관리 팁을 적용할 수 있습니다. 이는 객체의 범위를 제한하여 객체 수명을 단축시키거나, 불필요한 객체 참조를 제거하여 가비지 컬렉션의 대상이 되지 않도록 하는 것을 포함합니다. 또한, 적절한 객체 풀링이나 캐싱 전략을 적용하여 객체 생성과 해제를 최소화할 수 있습니다. 이러한 팁들은 가비지 컬렉션의 영향을 최소화하고 애플리케이션의 성능을 향상시킬 수 있습니다.
---
## 9. 가비지 컬렉션이 제대로 동작되도록 코드를 작성하는 방법
  (1) 불필요한 객체 참조 제거: 객체를 사용한 후에는 해당 객체의 참조를 명시적으로 제거해야 합니다. 예를 들어, 객체를 더 이상 사용하지 않는 경우 해당 참조를 null로 설정하여 GC가 해당 객체를 수집할 수 있도록 해야 합니다.

  (2) 정적 컬렉션 사용: 객체에 대한 참조를 정적 컬렉션에 저장하는 경우, 해당 컬렉션에서 제거해야 하는 시점에 주의해야 합니다. 정적 컬렉션은 애플리케이션 전체에서 유지되기 때문에, 불필요한 참조가 유지되는 경우 GC가 해당 객체를 해제할 수 없게 됩니다.

  (3) 불필요한 객체 생성 회피: 객체를 불필요하게 생성하는 것을 피해야 합니다. 예를 들어, 반복문 내에서 반복적으로 객체를 생성하는 대신, 객체를 한 번 생성하고 재사용하는 방법을 고려해야 합니다. 이렇게 하면 GC에 부담을 줄일 수 있습니다.

  (4) 순환 참조 회피: 순환 참조는 GC가 객체를 해제할 수 없게 만듭니다. 순환 참조를 회피하기 위해 약한 참조(Weak Reference)나 소프트 참조(Soft Reference)와 같은 GC가 영향을 주지 않는 참조 유형을 사용할 수 있습니다.

- 아래는 메모리 누수가 발생하는 예제 코드입니다:
``` java
public class MemoryLeakExample {
    private static List<Object> objectList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            Object object = new Object();
            objectList.add(object);
            System.out.println("Created new object: " + object);
        }
    }
}
``` 
=> 위의 예제 코드는 무한히 객체를 생성하고 objectList에 추가합니다. 이 코드에서는 불필요한 객체 참조 제거가 이루어지지 않기 때문에, objectList에 계속해서 객체가 쌓이게 되고 메모리 누수가 발생합니다. GC는 objectList의 객체를 해제할 수 없으므로 메모리가 계속해서 증가하게 됩니다.   

- 아래는 위의 메모리 누수를 해결하기 위한 수정된 예제코드 입니다:
``` java
public class NoMemoryLeakExample {
    private static List<Object> objectList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            Object object = new Object();
            objectList.add(object);
            System.out.println("Created new object: " + object);

            // 불필요한 객체 참조를 제거하여 GC가 수집할 수 있도록 함.
``` 
