import com.basho.riak.client.api.RiakClient
import com.basho.riak.client.api.cap.Quorum
import com.basho.riak.client.api.commands.kv.{DeleteValue, FetchValue, StoreValue}
import com.basho.riak.client.core.query.{Location, Namespace, RiakObject}
import com.basho.riak.client.core.util.BinaryValue

object Main {

  def main(args: Array[String]): Unit = {
    val client = RiakClient.newClient("127.0.0.1")
    val namespace = new Namespace("default", "bucket")
    val key = "that_is_key"
    var value = "and_that_is_value"
    save(client, namespace, key, value)
    println("val: " + load(client, namespace, key))
    value = "different_value!"
    save(client, namespace, key, value)
    println("val: " + load(client, namespace, key))
    remove(client, namespace, key)
    println("val: " + load(client, namespace, key))
    client.shutdown
  }


  @throws[InterruptedException]
  private def remove(client: RiakClient, ns: Namespace, key: String): Unit = {
    val location = new Location(ns, key)
    val delete = new DeleteValue.Builder(location).build
    client.execute(delete)
  }

  @throws[InterruptedException]
  private def save(client: RiakClient, ns: Namespace, key: String, value: String): Unit = {
    val location = new Location(ns, key)
    val riakObject = new RiakObject
    riakObject.setValue(BinaryValue.create(value))
    val store = new StoreValue.Builder(riakObject).withLocation(location).withOption(StoreValue.Option.W, new Quorum(3)).build
    client.execute(store)
  }

  @throws[InterruptedException]
  private def load(client: RiakClient, ns2: Namespace, key: String) = try {
    val location2 = new Location(ns2, key)
    val fv = new FetchValue.Builder(location2).build
    val response = client.execute(fv)
    val obj = response.getValue(classOf[RiakObject])
    obj.getValue.toString
  } catch {
    case e: NullPointerException =>
      "there is no value"
  }
}
