package org.itstep.backtracking;

import java.util.Scanner;
import java.util.Stack;

/*
Сумма степеней. Найти число способов, которым заданное число N может быть представвлено суммой степеней X различных натуральных чисел.
Например, если  N=100 и X=3, решением будет 1^3+2^3+3^3+4^3.
Поэтому вывод равен 1.
 */
public class PowerSum {

    private int count = 0, sum = 0;
    Stack<Integer> st = new Stack<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number and the power");
        int N = sc.nextInt();
        int X = sc.nextInt();
        PowerSum ps = new PowerSum();
        int count = ps.powSum(N, X);
        //printing the answer.
        System.out.println("Number of combinations of different natural number's raised to " + X + " having sum " + N + " are : ");
        System.out.println(count);
        sc.close();
    }

    public int powSum(int N, int X) {
        Sum(N, X, 1);
        return count;
    }

    //here i is the natural number which will be raised by X and added in sum.
    public void Sum(int N, int X, int i) {
        //if sum is equal to N that is one of our answer and count is increased.
        if (sum == N) {
            System.out.println(st.toString());
            count++;
            return;
        } //we will be adding next natural number raised to X only if on adding it in sum the result is less than N.
        else if (sum + power(i, X) <= N) {
            sum += power(i, X);
            st.push(i);
            Sum(N, X, i + 1);
            //backtracking and removing the number added last since no possible combination is there with it.
            sum -= power(i, X);
            if (!st.empty()) st.pop();
        }
        if (power(i, X) < N) {
            //calling the sum function with next natural number after backtracking if when it is raised to X is still less than X.
            Sum(N, X, i + 1);
        }
    }

    //creating a separate power function so that it can be used again and again when required. 
    private int power(int a, int b) {
        return (int) Math.pow(a, b);
    }
}
