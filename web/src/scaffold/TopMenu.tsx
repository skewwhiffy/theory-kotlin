import {Link} from "react-router";
import React from "react";
import {routes, PropsWithRoute} from "./routes";
import {Version} from "./Version";

const SingleLink = ({route: {path, link, menuText}}: PropsWithRoute) => {
  return <>{menuText && <Link key={path} to={link ?? path}>{menuText.toUpperCase()}</Link>}</>
}

const Links = () => {
  return <div className="flex justify-between w-full">
    <div className="flex-col flex justify-end">
      <div className="flex gap-2">
        {routes.map(it => <SingleLink key={it.path} route={it}/>)}
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
