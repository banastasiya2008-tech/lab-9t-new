package ua.opnu;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Task {
    public static void main(String[] args) {

    }

    // Завдання 1: з кожної пари видаляємо коротший рядок (при рівності - перший)
    public void removeShorterStrings(List<String> list) {
        int i = 0;
        while (i + 1 < list.size()) {
            if (list.get(i).length() <= list.get(i + 1).length()) {
                list.remove(i);
            } else {
                list.remove(i + 1);
            }
            // на позиції i залишився елемент, що "вижив"; наступна пара починається з i + 1
            i++;
        }
    }

    // Завдання 2: дублюємо кожен рядок
    public void stutter(List<String> list) {
        for (int i = 0; i < list.size(); i += 2) {
            list.add(i, list.get(i));
        }
    }

    // Завдання 3: міняємо місцями елементи в кожній парі
    public void switchPairs(List<String> list) {
        for (int i = 0; i + 1 < list.size(); i += 2) {
            String temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
    }

    // Завдання 4: видаляємо дублікати у відсортованому списку
    public void removeDuplicates(List<String> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).equals(list.get(i - 1))) {
                list.remove(i);
            }
        }
    }

    // Завдання 5: додаємо "****" перед кожним рядком довжиною 4
    public void markLength4(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++; // пропускаємо сам рядок з 4 символів
            }
        }
    }

    // Завдання 6: паліндром (вхідна черга залишається без змін)
    public boolean isPalindrome(Queue<Integer> queue) {
        Deque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();

        // копіюємо елементи у стек, прокручуючи чергу по колу
        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            stack.push(value);
            queue.add(value);
        }

        // порівнюємо початок черги з вершиною стеку (зворотний порядок)
        boolean result = true;
        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            int fromStack = stack.pop();
            queue.add(value);
            if (value != fromStack) {
                result = false;
            }
        }
        return result;
    }

    // Завдання 7: черга відсортована за модулем -> сортуємо з урахуванням знаку
    public void reorder(Queue<Integer> queue) {
        Deque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();

        // від'ємні відкладаємо у стек, невід'ємні лишаються у черзі
        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            if (value < 0) {
                stack.push(value);
            } else {
                queue.add(value);
            }
        }

        int nonNegative = queue.size();

        // зі стеку від'ємні виходять від найменшого до найбільшого
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // переносимо невід'ємні числа в кінець
        for (int i = 0; i < nonNegative; i++) {
            queue.add(queue.remove());
        }
    }

    // Завдання 8: спочатку парні, потім непарні (порядок зберігається)
    public void rearrange(Queue<Integer> queue) {
        Deque<Integer> stack = new ArrayDeque<>();
        int size = queue.size();

        // парні залишаються у черзі, непарні йдуть у стек
        for (int i = 0; i < size; i++) {
            int value = queue.remove();
            if (value % 2 == 0) {
                queue.add(value);
            } else {
                stack.push(value);
            }
        }

        int evens = queue.size();
        int odds = stack.size();

        // непарні додаємо в кінець (поки що у зворотному порядку)
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        // переносимо парні в кінець: [непарні (зворотно), парні]
        for (int i = 0; i < evens; i++) {
            queue.add(queue.remove());
        }

        // розвертаємо непарні назад через стек і додаємо після парних
        for (int i = 0; i < odds; i++) {
            stack.push(queue.remove());
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
    }

    // Завдання 9: довжина найдовшого рядка
    public int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    // Завдання 10: видаляємо рядки парної довжини
    public void removeEvenLength(Set<String> set) {
        set.removeIf(s -> s.length() % 2 == 0);
    }

    // Завдання 11: кількість унікальних спільних чисел
    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set = new HashSet<>(list1);
        set.retainAll(new HashSet<>(list2));
        return set.size();
    }

    // Завдання 12: усі значення унікальні?
    public boolean isUnique(Map<String, String> map) {
        Set<String> values = new HashSet<>(map.values());
        return values.size() == map.size();
    }

    // Завдання 13: перетин двох Map (однакові ключ і значення)
    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            Integer other = map2.get(entry.getKey());
            if (other != null && other.equals(entry.getValue())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    // Завдання 14: міняємо ключі та значення місцями
    // При однакових значеннях лишаємо пару з найбільшим ключем (цього очікують тести).
    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer old = result.get(entry.getValue());
            if (old == null || entry.getKey() > old) {
                result.put(entry.getValue(), entry.getKey());
            }
        }
        return result;
    }

    // Завдання 15: значення, що зустрічається найрідше (при рівності - найменше)
    public int rarest(Map<String, Integer> map) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : map.values()) {
            counts.merge(value, 1, Integer::sum);
        }

        int bestValue = 0;
        int bestCount = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int count = entry.getValue();
            int value = entry.getKey();
            if (count < bestCount || (count == bestCount && value < bestValue)) {
                bestCount = count;
                bestValue = value;
            }
        }
        return bestValue;
    }

    // Завдання 16: скільки разів зустрічається найпоширеніше число
    public int maxOccurrences(List<Integer> list) {
        Map<Integer, Integer> counts = new HashMap<>();
        int max = 0;
        for (int value : list) {
            int count = counts.merge(value, 1, Integer::sum);
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

}