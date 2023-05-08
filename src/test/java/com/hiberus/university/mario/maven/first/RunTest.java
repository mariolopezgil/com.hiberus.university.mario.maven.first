package com.hiberus.university.mario.maven.first;

import com.hiberus.university.mario.maven.first.Carrito.CarritoTest;
import com.hiberus.university.mario.maven.first.CheckOut.CheckoutTest;
import com.hiberus.university.mario.maven.first.Inventario.InventarioTest;
import com.hiberus.university.mario.maven.first.LogOut.LogOutTest;
import com.hiberus.university.mario.maven.first.Login.LoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({LoginTest.class, InventarioTest.class, CarritoTest.class, CheckoutTest.class, LogOutTest.class})

public class RunTest {

}

