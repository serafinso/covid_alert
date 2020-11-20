/*################################################################################################
*
* This file is the configuration of React Redux.
* Redux permit to store some data in the store. Variables are shared between all views
* It permits to store data to avoid multiple requests from server
* All Reducers are presents in the folder Reducers and routes are in the index.js
* Required module : redux
* Doc : https://www.npmjs.com/package/redux
* ################################################################################################*/


import { createStore } from 'redux'
import reducer from './Reducers/index'


export const store = createStore(reducer)
