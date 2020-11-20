import React, { Component } from "react";
import { connect } from "react-redux";

class Welcome extends Component {
  render() {
    return (
      <div className="Welcome">
        <p>This is your public-facing component.</p>
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  console.log(state);
  return {
    connectedUser: state.users.connectedUser,
  };
};
export default connect(mapStateToProps)(Welcome);
