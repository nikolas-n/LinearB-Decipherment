# This script is used for scraping http://minoan.deaditerranean.com/ which has the linear B corpus
library(rvest)
#website <- html("http://minoan.deaditerranean.com/linear-b-transliterations/knossos/kn-a/kn-ag/")
website <- html("http://minoan.deaditerranean.com/linear-b-transliterations/knossos/kn-o/kn-od/")

# knossos tablets
kna <- c("kn-ag", "kn-ai", "kn-am", "kn-ap", "kn-as")
knb <- c("kn-b")
knc <- c("kn-c-2")
knd <- c("kn-d")
kne <- c()
knf <- c("kn-fh", "kn-fp", "kn-fs", "kn-f")
kng <- c("kn-ga", "kn-gg", "kn-gm", "kn-gv", "kn-g")
knk <- c()
knl <- c("kn-lc", "kn-ld", "kn-le", "kn-ln", "kn-l")
knm <- c("kn-mc", "kn-m")
knn <- c("kn-nc", "kn-np")
kno <- c("kn-oa", "kn-od", "kn-og")
knpp <- c()
knr <- c("kn-ra", "kn-r")
kns <- c("kn-sc", "kn-sd", "kn-se", "kn-sf", "kn-sg", "kn-sk", "kn-so", "kn-sp")
knu <- c("kn-uc", "kn-uf", "kn-u")
knv <- c("kn-vc", "kn-vd", "kn-v")
knw <- c("kn-wb", "kn-wm", "kn-wn", "kn-ws")
knx <- c("kn-xd", "kn-xe", "km-xf", "kn-x")

#urls <- c("kn-a", "kn-b-2", "kn-c-3", "kn-d-2", "kn-e", "kn-f", "kn-g", "kn-k", "kn-l", "kn-m", "kn-n", "kn-o", "kn-pp", "kn-r-2",
#          "kn-s", "kn-u", "kn-v", "kn-w", "kn-x")

urls <- c(paste("kn-a/", kna, sep=""), paste("kn-b-2/", knb, sep=""), paste("kn-c-3/", knc, sep=""), paste("kn-d-2/", knd, sep=""),
          "kn-e", paste("kn-f/", knf, sep=""), paste("kn-g/", kng, sep=""), "kn-k", paste("kn-l/", knl, sep=""), paste("kn-m/", knm, sep=""),
          paste("kn-n/", knn, sep=""), paste("kn-o/", kno, sep=""), "kn-pp", paste("kn-r-2/", knr, sep=""), paste("kn-s/", kns, sep=""), paste("kn-u/", knu, sep=""),
          paste("kn-v/", knv, sep=""), paste("kn-w/", knw, sep=""), paste("kn-x/", knx, sep=""))

tablets<-c()

for(url in urls)
{
  fullurl = paste(paste("http://minoan.deaditerranean.com/linear-b-transliterations/knossos/", url, sep=""), "/", sep="")
  
  website <- html(fullurl)
  
  # gets title of the tablets
  website %>%
    html_nodes("h2") %>%
    html_text()
  
  # gets text from the tablets
  raw_data = website %>%
    html_nodes("p") %>%
    html_text()
  
  tablets = vector <- c()
  
  for (data in raw_data) {
    if(!(grepl(":", data)) && grepl("-[a-z]", data)) {
      tablets <- c(tablets, data)
    }
  }
  
}
