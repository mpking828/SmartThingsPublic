/**
 *  TestSlider
 *
 *  Copyright 2015 Ronald Gouldner
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
 
preferences {
    input("testOnOff", "enum", title: "Test Enum Preference ?", default:"On", options: ["On","Off"])
}

metadata {
	definition (name: "TestSlider", namespace: "gouldner", author: "Ronald Gouldner") {
		command "setTestValue"
		command "reportTestValue"
        command "setTestValue2"
		command "reportTestValue2"
		attribute "testValue", "NUMBER"
		attribute "testValue2", "NUMBER"
	}

	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		valueTile("value", "testValue") {
               state("value", label:'${currentValue}°',
                     backgroundColors:[
                     [value: 31, color: "#153591"],
                     [value: 44, color: "#1e9cbb"],
                     [value: 59, color: "#90d2a7"],
                     [value: 74, color: "#44b621"],
                     [value: 84, color: "#f1d801"],
                     [value: 95, color: "#d04e00"],
                     [value: 96, color: "#bc2323"]
                   ]
                   )
        }
		controlTile("valueSliderControl", "testValue", "slider", height: 1, width: 2, inactiveLabel: false, range:"(67..84)") {
			state "valueSliderControl", action:"setTestValue", backgroundColor:"#d04e00"
		}
        valueTile("value2", "testValue2") {
               state("value2", label:'${currentValue}°',
                     backgroundColors:[
                     [value: 31, color: "#153591"],
                     [value: 44, color: "#1e9cbb"],
                     [value: 59, color: "#90d2a7"],
                     [value: 74, color: "#44b621"],
                     [value: 84, color: "#f1d801"],
                     [value: 95, color: "#d04e00"],
                     [value: 96, color: "#bc2323"]
                   ]
                   )
        }
		controlTile("value2SliderControl", "testValue2", "slider", height: 1, width: 2, inactiveLabel: false) {
			state "value2SliderControl", action:"setTestValue2", backgroundColor:"#00ff00"
		}
		
		main (["value"])
		details(["value", "valueSliderControl","value2","value2SliderControl"])
	}
}

def setTestValue(int val) {
	log.debug "setTestValue called $val"
    //sendEvent("name":"testValue", "value":val)
    sendEvent("name":"testValue", "value":val, "isStateChange":true, unit:1, displayed:true)
}

def reportTestValue() {

	log.debug "reportTestValue called"
	
	def testValue = device.currentValue("testValue")
	
	if (testValue > 83) {
		sendEvent("name":"testValue", "value":67)
	} else {
		//sendEvent("name":"testValue", "value":testValue+1)
        sendEvent("name":"testValue", "value":testValue, "isStateChange":true, unit:1, displayed:true)
	}
}

def setTestValue2(int val) {
	log.debug "setTestValue2 called $val"
    sendEvent("name":"testValue2", "value":val, "isStateChange":true, unit:0, displayed:true)
}

def reportTestValue2() {

	log.debug "reportTestValue2 called"
	
	def testValue = device.currentValue("testValue2")
	
	if (testValue > 83) {
		sendEvent("name":"testValue2", "value":67)
	} else {
		//sendEvent("name":"testValue2", "value":testValue+1)
        sendEvent("name":"testValue2", "value":testValue, "isStateChange":true, unit:0, displayed:true)
	}
}
	

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
}