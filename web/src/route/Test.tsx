import {useState} from "react";
import axios from "axios";

export const Test = () => {
  const [version, setVersion] = useState<String | unknown>()

  const getVersion = async () => {
    try {
      const response = await axios.get("/api/ping")
      const {version} = await response.data
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