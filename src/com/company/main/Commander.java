package com.company.main;
import com.company.main.Foo;

class MyThread extends Thread
{
    Foo foo = new Foo();
    String name = "";
    String func = "";

    MyThread(String name, String func)
    {
        super(name);
        this.name = name;
        this.func = func;
    }

    public void run()
    {
        if(func.equals("foo1"))
        {
            System.out.print(name + ": ");
            foo.first();
            System.out.println();
            FirstChecker.set();
        }
        else if(func.equals("foo2"))
        {
            while(FirstChecker.get() == false)
            {
                try
                {
                    Thread.sleep(10);
                }
                catch(InterruptedException e)
                {
                }
            }
            System.out.print(name + ": ");
            foo.second();
            System.out.println();
            SecondChecker.set();
        }
        else if(func.equals("foo3"))
        {
            while(SecondChecker.get() == false)
            {
                try
                {
                    Thread.sleep(10);
                }
                catch(InterruptedException e)
                {
                }
            }
            System.out.print(name + ": ");
            foo.third();
            System.out.println();
        }
    }
}

public class Commander
{
    public static void main(String[] args)
    {
        new MyThread("C", "foo3").start();
        new MyThread("B", "foo2").start();
        new MyThread("A", "foo1").start();
    }
}
