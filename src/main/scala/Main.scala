

object Main extends App with Config {
  configuration.`acceptance-test`.admin.teams foreach println
}