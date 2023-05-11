package com.hiberus.university.mario.maven.first.Test;

import com.hiberus.university.mario.maven.first.Test.CarritoTest;
import com.hiberus.university.mario.maven.first.Test.CheckoutTest;
import com.hiberus.university.mario.maven.first.Test.InventarioTest;
import com.hiberus.university.mario.maven.first.Test.LogOutTest;
import com.hiberus.university.mario.maven.first.Test.LoginTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({LoginTest.class, InventarioTest.class, CarritoTest.class, CheckoutTest.class, LogOutTest.class})

public class RunTest {

}

