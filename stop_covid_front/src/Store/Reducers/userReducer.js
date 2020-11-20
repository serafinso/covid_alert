/*################################################################################################
*
* This file is the reducer of Users
* You can find an initial state and different actions
* When an action is called, we change state
* ################################################################################################*/


const initialState = {connectedUser:null}

function toggleUser(state = initialState, action) {
	let nextState
	switch(action.type) {
		case "TOGGLE_CONNECTED_USER":
			state = {connectedUser:action.connectedUser}
			return state
		default:
			return state
	}
}

export default toggleUser
