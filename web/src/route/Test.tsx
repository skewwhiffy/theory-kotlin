import {useState} from "react";
import {PingControllerApi} from "../api";

const pingController = new PingControllerApi(undefined, "")
export const Test = () => {
  const [version, setVersion] = useState<string | unknown>()

  const getVersion = async () => {
    try {
      const response = await pingController.ping()
      const {version} = response.data
      setVersion(version)
    } catch (error) {
      console.log(error)
    }
  }

  return <>
    <h1>Test sandbox</h1>
    <p>
      <>Version: {version ?? 'Unknown'}</>
    </p>
    <button onClick={() => getVersion()}>Get version</button>
  </>
}