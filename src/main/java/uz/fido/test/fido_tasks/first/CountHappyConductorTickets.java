package uz.fido.test.fido_tasks.first;

public class CountHappyConductorTickets {

    /**
     * 1) Создаю массив с возможной суммой от 0 до 27
     * 2) Подсчитываю количество комбинаций для каждой суммы
     * 3) Считаю сумму количества возможных счастливых билетов
     * произведению количества комбинаций первой тройки
     * на количество комбинаций второй тройки.
     *
     * @return Количество всех счастливых билетов 55252
     */
    public int count() {
        int[] possibleAmount = new int[28];
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 9; k++) {
                    possibleAmount[i + j + k]++;
                }
            }
        }
        int quantityLuckyTickets = 0;
        for (int x : possibleAmount) {
            quantityLuckyTickets += x * x;
        }
        return quantityLuckyTickets;
    }
}
