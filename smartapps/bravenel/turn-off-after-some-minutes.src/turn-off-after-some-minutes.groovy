/**
 *  Turn off after some minutes
 *
 *  Copyright 2015 Bruce Ravenel
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
    name: "Turn off after some minutes",
    namespace: "bravenel",
    author: "Bruce Ravenel",
    description: "Turn switches off after some number of minutes",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section("Turn switches off after some minutes") {
		input "switches", "capability.switch", title: "Switches", multiple: true, required:true
		input "minutes", "number", title: "Minutes", required: true
	}
}

def installed() {
	initialize()
}

def updated() {
	unsubscribe()
	initialize()
}

def initialize() {
	subscribe(switches, "switch", onHandler)
}

def onHandler(evt) {
	if(evt.value in ["on", "setLevel"]) runIn(minutes*60, turnOff)
}

def turnOff() {
	switches.off()
}