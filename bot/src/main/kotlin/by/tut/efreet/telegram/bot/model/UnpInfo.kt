package by.tut.efreet.telegram.bot.model

import by.tut.efreet.telegram.bot.helpers.JsonDDMMYYYYDateDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.io.Serializable
import java.util.Date

/**
 * POJO для хранения и отдачи информации по УНП
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class UnpInfo : Serializable {
    /**
     * УНП.
     */
    @JsonProperty("VUNP")
    var unp: Int = 0

    /**
     * Наименование полное.
     */
    @JsonProperty("VNAIMP")
    var name: String = ""

    /**
     * Наименование краткое.
     */
    @JsonProperty("VNAIMK")
    var nameShort: String = ""

    /**
     * Юр. адрес.
     */
    @JsonProperty("VPADRES")
    var address: String? = null

    /**
     * Дата регистрации
     */
    @JsonProperty("DREG")
    @JsonDeserialize(using = JsonDDMMYYYYDateDeserializer::class)
    var regDate: Date? = null

    /**
     * Код состояния по версии портала МНС.
     *
     * "1" - действующий (???)
     *
     * "L" - Ликвидирован
     *
     * "M" - в процессе ликвидации
     */
    @JsonProperty("CKODSOST")
    var state: String = ""

    /**
     * Код состояния (строкой)
     */
    @JsonProperty("VKODS")
    var stateDescr: String? = ""


    /**
     * дата изменения состояния плательщика.
     */
    @JsonProperty("DLIKV")
    @JsonDeserialize(using = JsonDDMMYYYYDateDeserializer::class)
    var changeDate: Date? = null

    /**
     * основание изменения состояния плательщика.
     */
    @JsonProperty("VLIKV")
    var changeReason: String? = null

    /**
     * Список филиалов (если они есть). Если филиалов нет, список пуст либо == null
     */
    var branches: List<Short>? = null

    /**
     * Признак существования контрагента. вычисляется на основании поля [state]
     */
    val existing
        get() = state != "L"

    companion object {
        private const val serialVersionUID: Long = 3
    }
}

/**
 * Сервис http://www.portal.nalog.gov.by/grp/getData?unp=500836423&type=json отдает информацию завернутой в еще один объект.
 * Вот он:
 */
class UnpInfoContainer : Serializable {
    @JsonProperty("ROW")
    var row: UnpInfo? = null

    companion object {
        private const val serialVersionUID: Long = 2
    }
}
