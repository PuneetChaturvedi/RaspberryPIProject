package maventest;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class BlinkingLed {

    public static void main(String[] args) throws InterruptedException {
    	  // get a handle to the GPIO controller
    	final GpioController gpio = GpioFactory.getInstance();
    	
    	 
    	 // creating the pin object
    	 final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
    	 
    	 // turn pin on and wait 2 seconds
    	 int x=0;
    	 while (x<100)
    	 {    	 
    		 pin.high();
	    	 System.out.println("Pin High");
	    	 Thread.sleep(1000);
	    	 
	    	 // turn off and wait 2 seconds
	    	 pin.low();
	    	 System.out.println("Pin Low");
	    	 Thread.sleep(1000);
	    	 
	    	// turn pin on for 2 second and then off
	    	 System.out.println("Pin High for 2 seconds");
	    	 pin.pulse(2000, true);
	    	 x++;
    	 }
    	 
    	 // release the GPIO controller resources
    	 gpio.shutdown();
    }
}