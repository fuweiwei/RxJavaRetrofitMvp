package com.veer.rx.model;

import java.util.List;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/7/2
 */

public class BookModel {

    /**
     * count : 1
     * start : 0
     * total : 1544
     * books : [{"rating":{"max":10,"numRaters":7760,"average":"9.4","min":0},"subtitle":"","author":["罗贯中"],"pubdate":"1986-6-1","tags":[{"count":1644,"name":"四大名著","title":"四大名著"},{"count":1511,"name":"三国演义","title":"三国演义"},{"count":1054,"name":"小说","title":"小说"},{"count":992,"name":"古典文学","title":"古典文学"},{"count":785,"name":"罗贯中","title":"罗贯中"},{"count":523,"name":"中国古典","title":"中国古典"},{"count":454,"name":"三国","title":"三国"},{"count":434,"name":"文学","title":"文学"}],"origin_title":"","image":"https://img3.doubanio.com/view/subject/m/public/s1661101.jpg","binding":"精装","translator":[],"catalog":"\n      ","pages":"638 页","images":{"small":"https://img3.doubanio.com/view/subject/s/public/s1661101.jpg","large":"https://img3.doubanio.com/view/subject/l/public/s1661101.jpg","medium":"https://img3.doubanio.com/view/subject/m/public/s1661101.jpg"},"alt":"https://book.douban.com/subject/1483894/","id":"1483894","publisher":"岳麓书社","isbn10":"7805200130","isbn13":"9787805200132","title":"三国演义","url":"https://api.douban.com/v2/book/1483894","alt_title":"三国志通俗演义","author_intro":"罗贯中（约1330\u2014约1400），汉族，名本，字贯中，号湖海散人。他的籍贯一说是太原（今山西太原) ，一说是东原(今山东东平）, 一说是钱塘（今浙江杭州)，不可确考。但是，近年来由于在山西省祁县河湾村发现了罗贯中之家谱，以及个人使用的印章，故基本可以确定其籍贯为太原府祁县。元末明初著名小说家、戏曲家，是中国章回小说的鼻祖。一生著作颇丰，主要作品有：剧本《赵太祖龙虎风云会》《忠正孝子连环谏》、《三平章死哭蜚虎子》；小说《隋唐两朝志传》《残唐五代史演义》《三遂平妖传》《粉妆楼》、和施耐庵合著的《水浒传》、代表作《三国演义》等。\n罗贯中生于元末明初的封建王朝时代。作为与\u201c倡优\u201d、\u201c妓艺\u201d为伍的戏曲平话作家，当时被视为勾栏瓦舍的下九流，正史不可能为他写经作传。惟一可看到的是一位明代无名氏编著的一本小册子《录鬼簿续编》，上写：\u201c罗贯中，太原人，号湖海散人。与人寡合，乐府隐语，极为清新。与余为忘年交，遭时多故，天各一方。至正甲辰复会，别来又六十余年，竟不知其所终。\u201d\n但从罗贯中的传世之作《三国演义》中，体现出罗贯中的博大精深之才，经天纬地之气。他精通军事学、心理学、智谋学、公关学、人才学\u2026\u2026如果没有超人的智慧，丰富的实践，执著的追求，何以能成为这般全才？他主张国家统一，热爱中华民族，弘扬民族传统美德，痛恨奸诈邪恶。在《残唐五代史演义》中，我们看到了罗贯中依恋故土、缅怀英雄、忧国忧民的高尚情操，他动情地写道：\n两岸西风起白杨，沁州存孝实堪伤。\n晋中花草埋幽径，唐国山河绕夕阳。\n鸦谷灭巢皆寂寞，并州尘路总荒凉。\n诗成不尽伤情处，一度行吟一断肠。","summary":"滚滚长江东逝水，浪花淘尽英雄。吕布赵云关羽，官渡赤壁街亭，斩华雄空城计长坂坡七擒七纵，一看三叹，三国风云起，几度夕阳红。该剧展现了历史上一个豪强们为攫取最高统治权而进行的政治斗争和频繁混战的动乱时代。展示了魏、蜀、吴纵横捭阖、逐鹿争雄的历史画卷！\n《三国演义》是根据三国时期的史实和民间传说创作而成的优秀历史小说。它的内容丰富多彩，为读者留下了深厚多而的认识价值。故事远起汉灵帝年间刘、关、张桃园结义，描述了东汉末年和三国时期近百年发生的重大历史事件，和众多的叱咤风云的英雄人物。作者通过真实动人的故事，揭示了封建统治阶级内部的黑暗和腐朽，控诉了统治者的暴虐和丑恶。东汉末年，军阀混战，所谓\u201c十八路\u201d诸候联军征讨董卓，打的是\u201c扶持王室，拯救黎民\u201d的旗号，干的是勾心斗角、尔虞我诈的勾当，各怀心复事，都企图称王称霸。","series":{"id":"10185","title":"古典名著普及文库"},"price":"13.0"}]
     */

    private int count;
    private int start;
    private int total;
    private List<BooksBean> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        /**
         * rating : {"max":10,"numRaters":7760,"average":"9.4","min":0}
         * subtitle :
         * author : ["罗贯中"]
         * pubdate : 1986-6-1
         * tags : [{"count":1644,"name":"四大名著","title":"四大名著"},{"count":1511,"name":"三国演义","title":"三国演义"},{"count":1054,"name":"小说","title":"小说"},{"count":992,"name":"古典文学","title":"古典文学"},{"count":785,"name":"罗贯中","title":"罗贯中"},{"count":523,"name":"中国古典","title":"中国古典"},{"count":454,"name":"三国","title":"三国"},{"count":434,"name":"文学","title":"文学"}]
         * origin_title :
         * image : https://img3.doubanio.com/view/subject/m/public/s1661101.jpg
         * binding : 精装
         * translator : []
         * catalog :

         * pages : 638 页
         * images : {"small":"https://img3.doubanio.com/view/subject/s/public/s1661101.jpg","large":"https://img3.doubanio.com/view/subject/l/public/s1661101.jpg","medium":"https://img3.doubanio.com/view/subject/m/public/s1661101.jpg"}
         * alt : https://book.douban.com/subject/1483894/
         * id : 1483894
         * publisher : 岳麓书社
         * isbn10 : 7805200130
         * isbn13 : 9787805200132
         * title : 三国演义
         * url : https://api.douban.com/v2/book/1483894
         * alt_title : 三国志通俗演义
         * author_intro : 罗贯中（约1330—约1400），汉族，名本，字贯中，号湖海散人。他的籍贯一说是太原（今山西太原) ，一说是东原(今山东东平）, 一说是钱塘（今浙江杭州)，不可确考。但是，近年来由于在山西省祁县河湾村发现了罗贯中之家谱，以及个人使用的印章，故基本可以确定其籍贯为太原府祁县。元末明初著名小说家、戏曲家，是中国章回小说的鼻祖。一生著作颇丰，主要作品有：剧本《赵太祖龙虎风云会》《忠正孝子连环谏》、《三平章死哭蜚虎子》；小说《隋唐两朝志传》《残唐五代史演义》《三遂平妖传》《粉妆楼》、和施耐庵合著的《水浒传》、代表作《三国演义》等。
         罗贯中生于元末明初的封建王朝时代。作为与“倡优”、“妓艺”为伍的戏曲平话作家，当时被视为勾栏瓦舍的下九流，正史不可能为他写经作传。惟一可看到的是一位明代无名氏编著的一本小册子《录鬼簿续编》，上写：“罗贯中，太原人，号湖海散人。与人寡合，乐府隐语，极为清新。与余为忘年交，遭时多故，天各一方。至正甲辰复会，别来又六十余年，竟不知其所终。”
         但从罗贯中的传世之作《三国演义》中，体现出罗贯中的博大精深之才，经天纬地之气。他精通军事学、心理学、智谋学、公关学、人才学……如果没有超人的智慧，丰富的实践，执著的追求，何以能成为这般全才？他主张国家统一，热爱中华民族，弘扬民族传统美德，痛恨奸诈邪恶。在《残唐五代史演义》中，我们看到了罗贯中依恋故土、缅怀英雄、忧国忧民的高尚情操，他动情地写道：
         两岸西风起白杨，沁州存孝实堪伤。
         晋中花草埋幽径，唐国山河绕夕阳。
         鸦谷灭巢皆寂寞，并州尘路总荒凉。
         诗成不尽伤情处，一度行吟一断肠。
         * summary : 滚滚长江东逝水，浪花淘尽英雄。吕布赵云关羽，官渡赤壁街亭，斩华雄空城计长坂坡七擒七纵，一看三叹，三国风云起，几度夕阳红。该剧展现了历史上一个豪强们为攫取最高统治权而进行的政治斗争和频繁混战的动乱时代。展示了魏、蜀、吴纵横捭阖、逐鹿争雄的历史画卷！
         《三国演义》是根据三国时期的史实和民间传说创作而成的优秀历史小说。它的内容丰富多彩，为读者留下了深厚多而的认识价值。故事远起汉灵帝年间刘、关、张桃园结义，描述了东汉末年和三国时期近百年发生的重大历史事件，和众多的叱咤风云的英雄人物。作者通过真实动人的故事，揭示了封建统治阶级内部的黑暗和腐朽，控诉了统治者的暴虐和丑恶。东汉末年，军阀混战，所谓“十八路”诸候联军征讨董卓，打的是“扶持王室，拯救黎民”的旗号，干的是勾心斗角、尔虞我诈的勾当，各怀心复事，都企图称王称霸。
         * series : {"id":"10185","title":"古典名著普及文库"}
         * price : 13.0
         */

        private RatingBean rating;
        private String subtitle;
        private String pubdate;
        private String origin_title;
        private String image;
        private String binding;
        private String catalog;
        private String pages;
        private ImagesBean images;
        private String alt;
        private String id;
        private String publisher;
        private String isbn10;
        private String isbn13;
        private String title;
        private String url;
        private String alt_title;
        private String author_intro;
        private String summary;
        private SeriesBean series;
        private String price;
        private List<String> author;
        private List<TagsBean> tags;
        private List<?> translator;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public String getOrigin_title() {
            return origin_title;
        }

        public void setOrigin_title(String origin_title) {
            this.origin_title = origin_title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBinding() {
            return binding;
        }

        public void setBinding(String binding) {
            this.binding = binding;
        }

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getIsbn10() {
            return isbn10;
        }

        public void setIsbn10(String isbn10) {
            this.isbn10 = isbn10;
        }

        public String getIsbn13() {
            return isbn13;
        }

        public void setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAlt_title() {
            return alt_title;
        }

        public void setAlt_title(String alt_title) {
            this.alt_title = alt_title;
        }

        public String getAuthor_intro() {
            return author_intro;
        }

        public void setAuthor_intro(String author_intro) {
            this.author_intro = author_intro;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public SeriesBean getSeries() {
            return series;
        }

        public void setSeries(SeriesBean series) {
            this.series = series;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public List<String> getAuthor() {
            return author;
        }

        public void setAuthor(List<String> author) {
            this.author = author;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public List<?> getTranslator() {
            return translator;
        }

        public void setTranslator(List<?> translator) {
            this.translator = translator;
        }

        public static class RatingBean {
            /**
             * max : 10
             * numRaters : 7760
             * average : 9.4
             * min : 0
             */

            private int max;
            private int numRaters;
            private String average;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getNumRaters() {
                return numRaters;
            }

            public void setNumRaters(int numRaters) {
                this.numRaters = numRaters;
            }

            public String getAverage() {
                return average;
            }

            public void setAverage(String average) {
                this.average = average;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/subject/s/public/s1661101.jpg
             * large : https://img3.doubanio.com/view/subject/l/public/s1661101.jpg
             * medium : https://img3.doubanio.com/view/subject/m/public/s1661101.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class SeriesBean {
            /**
             * id : 10185
             * title : 古典名著普及文库
             */

            private String id;
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class TagsBean {
            /**
             * count : 1644
             * name : 四大名著
             * title : 四大名著
             */

            private int count;
            private String name;
            private String title;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
