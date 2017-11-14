package com.flexicious.example.serviceproxies;
             
import java.util.Arrays;
import java.util.List;

import com.flexicious.controls.core.FlexTimer;
import com.flexicious.controls.core.Function;
import com.flexicious.controls.core.TimerEvent;

public class ServiceProxyBase {

	public void callServiceMethod(Object token, Function resultFunction, Function faultHandler) {
		FlexTimer timer = new FlexTimer(2000, 1);
		timer.addEventListener(TimerEvent.TIMER_COMPLETE, new Function(this,
				"onTimer"));

		timer.tag = Arrays.asList(token, resultFunction);
		timer.start();
	}

	@SuppressWarnings("unchecked")
	public void onTimer(TimerEvent event) {
		FlexTimer t = (FlexTimer) event.currentTarget; 
		 
		List<Object> results = (List<Object>) t.tag;
		Object result = results.get(0);
		Function callBack = (Function) results.get(1);
		callBack.execute(result);
	}
}
