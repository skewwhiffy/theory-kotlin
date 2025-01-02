import {Link} from "react-router";
import React from "react";
import {routes} from "./routes";
import {Version} from "./Version";

const Links = () => {
  return <div className="flex justify-between w-full">
    <div className="flex-col flex justify-end">
      <div className="flex gap-2">
        {routes
          .filter(it => it.menuText)
          .map(({path, menuText}) => <Link key={path} to={path}>{menuText?.toUpperCase()}</Link>)}
      </div>
    </div>
    <Version/>
  </div>
}
export const TopMenu = () => {
  return <div className="bg-yellow-50 h-24 p-6 flex gap-6">
    <div className="text-lg font-bold font-sans text-blue-500">Theory tester</div>
    <Links/>
  </div>
}
