package is.swan.mcmarketapi.request.requests.resource.download;

import com.google.gson.JsonElement;
import is.swan.mcmarketapi.classes.Download;
import is.swan.mcmarketapi.request.Request;
import is.swan.mcmarketapi.request.sorting.SortOptions;
import is.swan.mcmarketapi.utils.SortUtil;

public class ListResourceDownloadsByVersionRequest implements Request<Download[]> {

    private final int resourceId, versionId;
    private final SortOptions sortOptions;

    public ListResourceDownloadsByVersionRequest(int resourceId, int versionId, SortOptions sortOptions) {
        this.resourceId = resourceId;
        this.versionId = versionId;
        this.sortOptions = sortOptions;
    }

    @Override
    public String getURL() {
        return BASE_URL + "/resources/" + resourceId + "/downloads/versions/" + versionId + SortUtil.optionsToString(sortOptions);
    }

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public Download[] handleJson(String json) {
        JsonElement element = GSON.fromJson(json, JsonElement.class);
        String resourceDownloadsJson = element.getAsJsonObject().get("data").getAsJsonArray().toString();
        Download[] resourceDownloads = GSON.fromJson(resourceDownloadsJson, Download[].class);

        return resourceDownloads;
    }
}
