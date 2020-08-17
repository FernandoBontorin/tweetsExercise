package tweets.io

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import tweets.{Empty, Tweet, TweetSet}

import scala.io.Source

object DataTweetsReader {

  def getTweets(path: String): Map[String, List[Tweet]] = {
    new java.io.File(path)
      .listFiles()
      .map(
        file =>
          removeJsonExtension(file.getName) ->
            jsonMapperMap(getFileContent(f"$path/${file.getName}")).map(toTweet)
      )
      .toMap
  }

  def toTweet(tweetMapSource: Map[String, Object]): Tweet = {
    Tweet(
      tweetMapSource.getOrElse("user", new Object).toString,
      tweetMapSource.getOrElse("text", new Object).toString,
      tweetMapSource.getOrElse("retweets", new Object).toString.toDouble
    )

  }


  private def getFileContent(path: String): String = {
    val handle = Source.fromFile(path)
    val content = handle.getLines.mkString
    handle.close
    content
  }

  private def removeJsonExtension(fileName: String) = fileName.dropRight(5)

  private def jsonMapperMap(json: String) = {
    val mapper = new ObjectMapper() with ScalaObjectMapper
    mapper.registerModule(DefaultScalaModule)
    mapper
      .readValue[List[Map[String, Object]]](
        json,
        classOf[List[Map[String, Object]]]
      )
  }

  def toTweetSet(l: List[Tweet]): TweetSet = {
    l.foldLeft(new Empty: TweetSet)(_.incl(_))
  }

  private def mapToTweet(maps: List[Map[String, Object]]): List[Tweet] = {
    maps.map(
      line =>
        Tweet(
          line.getOrElse("user", new Object).toString,
          line.getOrElse("text", new Object).toString,
          line.getOrElse("retweets", new Object).toString.toDouble
      )
    )
  }

}
