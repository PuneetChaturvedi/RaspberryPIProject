package maventest;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class PushButtonTest  {

    public static void main(String[] args) throws InterruptedException {
    	  // get a handle to the GPIO controller
    	final GpioController gpio = GpioFactory.getInstance();
    	
    	 
    	 // creating the pin object
    	 final GpioPinDigitalOutput pout = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
    	 final GpioPinDigitalInput pin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29,"my",PinPullResistance.PULL_UP);
    	 
    	 System.out.println(pin.getState());
     pin.addListener(new GpioUsageExampleListener(pout));
     System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");

     // keep program running until user aborts (CTRL-C)
     try {
     while(true) {
         Thread.sleep(500);
     }
     }
     // stop all GPIO activity/threads by shutting down the GPIO controller
     // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
     catch(Exception e) {
    	 	System.out.println("shutting down");
    	 	gpio.shutdown();
     }
    }
}

 class GpioUsageExampleListener implements GpioPinListenerDigital {
	 GpioPinDigitalOutput pout;
	 public GpioUsageExampleListener(GpioPinDigitalOutput pout) {
		 this.pout=pout;
	 }
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
        // display pin state on console
        System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = "
                + event.getState());
        if(event.getState()== PinState.LOW){
        		pout.setState(PinState.HIGH);
        }else {
        	pout.setState(PinState.LOW);
        }
        
    }
}