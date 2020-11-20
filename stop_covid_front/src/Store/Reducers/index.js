/*################################################################################################
*
* This file is the roots of React Redux.
* Redux permit to store some data in the store. Variables are shared between all views
* It permits to store data to avoid multiple requests from server
* You can find here all prefix to link the reducers
*
* Doc : https://www.npmjs.com/package/redux
* Examples in the mapStateToProps in views
* ################################################################################################*/

import { combineReducers } from 'redux';
import toggleUser from './userReducer'


export default combineReducers({
	users: toggleUser,
});
