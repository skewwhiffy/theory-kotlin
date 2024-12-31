import {Link} from "react-router";
import React from "react";

export const TopMenu = () => {
  return <div className="bg-yellow-50 h-24 p-6 flex gap-6">
    <div className="text-4xl font-bold font-sans text-blue-500">Theory tester</div>
    <Link to="">home</Link>
    <Link to="test">test</Link>
  </div>
}
