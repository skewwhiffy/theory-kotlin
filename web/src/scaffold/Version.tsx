import {PingControllerApi} from "../api";
import {useQuery} from "@tanstack/react-query";

const pingController = new PingControllerApi(undefined, "")
export const Version = () => {
  const query = useQuery({
    queryKey: ["get-version"],
    queryFn: async () => {
      const response = await pingController.ping()
      const {version} = response.data
      return version
    }
  })

  return <div>Version: {query.data ?? 'unknown'}</div>
}