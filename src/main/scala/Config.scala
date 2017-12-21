import com.typesafe.config.{ConfigFactory, ConfigRenderOptions}
import jsonmodels.configuration.Configuration
import play.api.libs.json.Json

trait Config {
  private val config = ConfigFactory.load()
  private val configJsonString = config.root().render(ConfigRenderOptions.concise())
  val configuration: Configuration = Json.parse(configJsonString).as[Configuration]
}
