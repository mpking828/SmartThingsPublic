/**
 *  Virtual On/Off Switch Creator
 *
 *  Copyright 2016 Ronald Gouldner 
 *  
 *  Code based on baldeagle072 : Virtual On/Off Switch Creator  
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
definition(
    name: "Virtual Stateless On/Off Button Creator",
    namespace: "gouldner",
    author: "Ronald Gouldner",
    description: "Creates virtual stateless on/off switches!",
    category: "Convenience",
    iconUrl: "http://cdn.device-icons.smartthings.com/Home/home30-icn.png",
    iconX2Url: "http://cdn.device-icons.smartthings.com/Home/home30-icn@2x.png")


preferences {
	section("Create Virtual Stateless Switch") {
		input "switchLabel", "text", title: "Switch Label", required: true
	}
}

def installed() {
	log.debug "Installed with settings: ${settings}"
	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"
	unsubscribe()
	initialize()
}

def initialize() {
    log.debug(deviceId)
    def deviceId = app.id + "SimulatedSwitch"
    def existing = getChildDevice(deviceId)
    if (!existing) {
        def childDevice = addChildDevice("gouldner", "Stateless On-Off Button Tile", deviceId, null, [label: switchLabel])
    }
}

def uninstalled() {
    removeChildDevices(getChildDevices())
}

private removeChildDevices(delete) {
    delete.each {
        deleteChildDevice(it.deviceNetworkId)
    }
}