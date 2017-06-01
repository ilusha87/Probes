package javaFailures;

/**
 * Created by Illia Krysenko on 30.01.2017.
 */
interface Pong<T> {}
class Ping<T> implements Pong<Pong<? super Ping<Ping<T>>>> {
    static void Ping() {
        //Pong<? super Ping<Long>> ping = new Ping<Long>();
    }
}
